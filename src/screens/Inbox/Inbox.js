import React, { useEffect, useState } from 'react'
import { FlatList, SafeAreaView, View, StyleSheet, Text, TouchableOpacity } from 'react-native';
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton';
import endpoint from '../../helpers/endpoint';
import Button from '../../components/Button';

export default function Inbox({ navigation, route }) {

  const { id } = route.params;
    const [mail, setMail] = useState([]);

  useEffect(() => { 
    fetch(`${endpoint}/message/inbox/${parseInt(id)}`).then(
      res => res.json()
    ).then(data => {
      data[0] && data.map(async message => {
        fetch(`${endpoint}/user/${message.senderID}`, { method: 'GET' })
        .then(res => res.json())
          .then((info) => { setMail([ ...mail, { ...message, from: info }]) })
    });
      });
      
  }, [])
  
  // this.messageID = messageID;
  //       text = str;
  //       this.senderID = senderID;
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
      <Button style={{width: '100%'}}onPress={() => { navigation.navigate("NewMessage", {senderID: id, recieverID: 0}) } } mode="contained"> New Message </Button>
      <SafeAreaView style={style.container}>
        <FlatList
        data={mail}
          renderItem={({ item }) => (
            <View key={item.id}  style={{ ...style.button, height: 'auto', paddingBottom: 4}} >
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, }}>
                      <View style={{margin: 4, width: 85 }}>
                          <Text style={{ overflow: 'hidden'}}>From: {item.from?.name}</Text>
                      </View>
                  <Text style={{ margin: 4, width: 190 }}>
                    {item.text}
                  </Text>
                </View>
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, justifyContent: 'space-between' }}>
                <TouchableOpacity onPress={() => { navigation.navigate("NewMessage", { senderID: id, recieverID: item.from.id }) }}>
                      <Text>Respond</Text>
                    </TouchableOpacity>
                <TouchableOpacity onPress={() => {
                  fetch(`${endpoint}/message/delete/${item.messageID}`).then(
                  setMail(mail.filter(m => m.messageID != item.messageID)))
                }}>
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

