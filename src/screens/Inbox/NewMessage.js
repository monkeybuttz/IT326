import React, { useState } from 'react'
import { View, StyleSheet, TouchableOpacity } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../../components/Background'
import Header from '../../components/Header'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { emailValidator } from '../../helpers/emailValidator'
import { phoneValidator } from '../../helpers/phoneValidator'
import { passwordValidator } from '../../helpers/passwordValidator'
import { nameValidator } from '../../helpers/nameValidator'


export default function NewMessage({ navigation }) {
  const [name, setName] = useState({ value: '', error: '' })
    const [subject, setSubject] = useState({ value: '', error: '' })
    const [content, setContent] = useState({ value: '', error: '' })


  const onSendPressed = () => {
    const nameError = nameValidator(name.value)
    const subjectError = nameValidator(subject.value)
    const contentError = nameValidator(content.value)
    if (contentError || subjectError || nameError) {
      setName({ ...name, error: nameError })
      setSubject({ ...subject, error: subjectError })
      setContent({ ...content, error: contentError })
      return
    }
    navigation.reset({
      index: 0,
      routes: [{ name: 'VerifyEmail' }],
    })
  }
    

  return (
    <Background>
          <BackButton goBack={navigation.goBack} />
      <Header>My Information</Header>
      <TextInput
        label="To"
        returnKeyType="next"
        value={name.value}
        onChangeText={(text) => setName({ value: text, error: '' })}
        error={!!name.error}
        errorText={name.error}
      />
      <TextInput
        label="subject"
        returnKeyType="next"
        value={subject.value}
        onChangeText={(text) => setSubject({ value: text, error: '' })}
        error={!!subject.error}
        errorText={subject.error}
      />
      <TextInput
        label="Message"
        returnKeyType="next"
        value={content.value}
        onChangeText={(text) => setSubject({ value: text, error: '' })}
        error={!!content.error}
        errorText={content.error}
      />
      <Button
        mode="send"
        onPress={onSendPressed}
        style={{ marginTop: 24, color: theme.colors.secondary }}
      >
        Update Account
      </Button>
      <View style={styles.row}>
        <Text> Time for a change? </Text>
        <TouchableOpacity onPress={() => navigation.replace('DeleteAccount')}>
          <Text style={styles.link}>Delete Account</Text>
        </TouchableOpacity>
      </View>
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
