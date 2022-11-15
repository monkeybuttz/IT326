import React, { useState } from 'react'
import { FlatList, SafeAreaView, View, Image, StyleSheet, Text, TouchableOpacity } from 'react-native';
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Header from '../../components/Header'
import { theme } from '../../core/theme';
import BackButton from '../../components/BackButton';
import Button from '../../components/Button';

export default function Inbox({ navigation }) {

    const [mail, setMail] = useState([{ open: true, subject: "hi", id: 2, from: {name: "Omar", id: 1}, content: "a very long message that will not fit inside the small view screen"}, {}, {}, {} ]);

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
            item.open ? 
              <View style={{ ...style.button, height: 'auto', paddingBottom: 4}} >
                <TouchableOpacity style={{alignSelf: 'flex-end', marginRight: 4}}>
                  <Text style={{fontSize: 16}}>x</Text>
                    </TouchableOpacity>
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
            : <TouchableOpacity style={style.button}
                onClick={() => {
                    setMail(mail.map(m => {
                      return (
                       (m == item) ? { ...m, open: true } : { ...m, open: false }
                      )
                    } ) )
                  } }
            >
                  <View style={{ flex: 1, flexDirection: 'row', margin: 1, }}>
                      <View style={{margin: 4, width: 85 }}>
                        <Text>Sub: {item.subject}</Text>
                          <Text style={{ overflow: 'hidden'}}>From: {item.from?.name}</Text>
                      </View>
                  <Text style={{ margin: 4, width: 190 }}>
                    {item.content?.substring(0, item.content?.length > 55 ? 55 : item.content?.length)}
                    ...
                  </Text>
                </View>
                {item.open && <View style={{ flex: 1, flexDirection: 'row', margin: 1, justifyContent: 'space-between' }}>
                <TouchableOpacity>
                  <Text>Respond</Text>
                </TouchableOpacity>
                <TouchableOpacity>
                  <Text> Delete </Text>
                </TouchableOpacity>
                </View>}
            </TouchableOpacity>
            
        )}
        //Setting the number of column
        numColumns={mail.length > 6 ? 2 : 1}
        keyExtractor={(item, index) => index}
      />
    </SafeAreaView>
    </Background>
  )
}

