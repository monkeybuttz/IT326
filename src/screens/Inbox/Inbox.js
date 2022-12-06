import React, { useEffect, useState } from 'react'
import { FlatList, SafeAreaView, View, StyleSheet, Text, TouchableOpacity } from 'react-native';
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton';
import endpoint from '../../helpers/endpoint';
import Button from '../../components/Button';

export default function Inbox({ navigation, route }) {

  const { id } = route.params;
    const [mail, setMail] = useState([{ subject: "hi", messageId: 2, from: {name: "Omar", id: 1}, content: "a very long message that will not fit inside the small view screen"} ]);

  useEffect(() => { 
    fetch(`${endpoint}/message/inbox/${id}`).then(
      res => res.json()
    ).then(data => {
      data[0] && data.forEach(message => {
        fetch(`${endpoint}/user/${message.senderId}`, { method: 'GET' })
        .then(res => res.json())
        .then((info) => { setMail([...mail, {...message, from: info}]) })
    });
      });
      
  }, [])
  
  // this.messageId = messageId;
  //       text = str;
  //       this.senderId = senderId;
  //       this.receiverId = receiverId;
  
  const style = StyleSheet.create({
    container: {
      flex: 1,
      borderRadius: 9999,
    },
    button: {
        width: 298,
        margin: 2,
        borderColor: 'black',
      borderWidth: 1,
        height: 50
    }
  });

  return (
    <Background>
          <BackButton goBack={navigation.goBack} />
      <View style={{ margin: 12 }}></View>
      <Header>GroomBuddy</Header>
      <Button style={{width: '100%'}}onPress={() => { navigation.navigate("NewMessage", {senderId: id, recieverId: 0}) } } mode="contained"> New Message </Button>
      <SafeAreaView style={style.container}>
        <FlatList
        data={mail}
          renderItem={({ item }) => (
              <View style={{ ...style.button, height: 'auto', paddingBottom: 4}} >
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, }}>
                      <View style={{margin: 4, width: 85 }}>
                          <Text style={{ overflow: 'hidden'}}>From: {item.from?.name}</Text>
                      </View>
                  <Text style={{ margin: 4, width: 190 }}>
                    {item.content}
                  </Text>
                </View>
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, justifyContent: 'space-between' }}>
                <TouchableOpacity onPress={() => { navigation.navigate("NewMessage", { senderId: id, recieverId: item.recieverId }) }}>
                      <Text>Respond</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => {setMail(mail.filter(m => m.messageId != item.messageId))}}>
                      <Text> Delete </Text>
                    </TouchableOpacity>
                    </View>
                  </View>
        )}
        //Setting the number of column
        numColumns={1}
        keyExtractor={(item, index) => index}
      />
    </SafeAreaView>
    </Background>
  )
}

