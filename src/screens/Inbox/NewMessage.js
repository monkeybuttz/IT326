import React, { useEffect, useState } from 'react'
import { View, StyleSheet, TouchableOpacity } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../../components/Background'
import Header from '../../components/Header'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { nameValidator } from '../../helpers/nameValidator'
import endpoint from '../../helpers/endpoint'

export default function NewMessage({ navigation, route }) {

  const { senderID, recieverID } = route.params;

  const [content, setContent] = useState({ value: '', error: '' })
  const [groomer, setGroomer] = useState({ name: "", id: -1 });
  const [groomers, setGroomers] = useState([]);

  useEffect(() => {
    if (recieverID) {
      fetch(`${endpoint}/user/${recieverID}`).then(res => res.json())
        .then(data => setGroomer(data));
}},[])  

useEffect(() => {
    if (groomer.name.length > 0 && groomer.id == -1) {
      fetch(`${endpoint}/searchForGroomer/${groomer.name}`)
      .then(res => { const resp = res.json(); if (resp) { return resp } else { return []} })
      .then(data => setGroomers([...data])
      );
    }
  }, [groomer])
  
  const onSendPressed = () => {
    const contentError = nameValidator(content.value)
    if (contentError) {
      setContent({ ...content, error: contentError })
      return
    }
    fetch(`${endpoint}/message/${senderID}/${groomer.id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ messageID: 0, senderID: senderID, recieverID: groomer.id, text: content.value })
    }).then(res => res.json()).then(navigation.navigate('Home', {id: senderID}));
  }
    

  return (
    <Background>
          <BackButton goBack={navigation.goBack} />
      <Header>New Message</Header>
      <View style={{ width: '100%'}}>
          <TextInput
                label="To"
                returnKeyType="next"
                value={groomer.name}
          onChangeText={(text) => setGroomer({name:text, id: -1})} 
        />
        {(groomer.id == -1 && groomer.name?.length > 0) && <View>
          {groomers.map(g => {
            return <TouchableOpacity style={{ width: '100%', fontSize: 13, borderColor: theme.colors.secondary,
              borderWidth: 1, padding: 12, borderRadius: 4
            }}
              onPress={() => setGroomer(g)}
            >
          <Text  style={{ width: '100%', fontSize: 15, }}>{g.name}</Text>
        </TouchableOpacity>
        })}
        </View>}
      </View>
      <TextInput
        label="Message"
        returnKeyType="next"
        value={content.value}
        onChangeText={(text) => setContent({ value: text, error: '' })}
        error={!!content.error}
        errorText={content.error}
      />
      <Button
        mode="contained"
        onPress={onSendPressed}
        style={{ marginTop: 24, color: theme.colors.secondary }}
      >
        Send
      </Button>
    </Background>
  )
}

const styles = StyleSheet.create({
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
})
