import React, { useState } from 'react'
import { FlatList, SafeAreaView, View, StyleSheet, Text, TouchableOpacity } from 'react-native';
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton';

export default function Inbox({ navigation }) {

    const [mail, setMail] = useState([{ subject: "hi", id: 2, from: {name: "Omar", id: 1}, content: "a very long message that will not fit inside the small view screen"}, {}, {}, {} ]);

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
          <View style={{margin: 12}}></View>
      <Header>GroomBuddy</Header>
      <SafeAreaView style={style.container}>
      <FlatList
        data={mail}
          renderItem={({ item }) => (
              <View style={{ ...style.button, height: 'auto', paddingBottom: 4}} >
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, }}>
                      <View style={{margin: 4, width: 85 }}>
                        <Text>Sub: {item.subject}</Text>
                          <Text style={{ overflow: 'hidden'}}>From: {item.from?.name}</Text>
                      </View>
                  <Text style={{ margin: 4, width: 190 }}>
                    {item.content}
                  </Text>
                </View>
                <View style={{ flex: 1, flexDirection: 'row', margin: 1, justifyContent: 'space-between' }}>
                    <TouchableOpacity>
                      <Text>Respond</Text>
                    </TouchableOpacity>
                    <TouchableOpacity onPress={() => {setMail(mail.filter(m => m.id != item.id))}}>
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

