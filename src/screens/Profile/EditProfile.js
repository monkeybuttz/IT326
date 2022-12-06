import React, { useEffect, useState } from 'react'
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
import endpoint from '../../helpers/endpoint'


export default function EditProfile({ navigation, route }) {

  const { id } = route.params;

  const [name, setName] = useState({ value: '', error: '' })
  const [phone, setPhone] = useState({ value: '', error: '' })
  const [email, setEmail] = useState({ value: '', error: '' })

  useEffect(() => {
    fetch(`${endpoint}/user/${id}`, { method: 'GET' })
      .then(res => res.json())
      .then((data) => {
        setName({value: data.name, error : ''});
        setPhone({ value: data.phoneNumber.toString(), error: '' });
        setEmail({value: data.email, error : ''})
      }).catch()
  }, [])

  function onUpdatePressed() {
    const nameError = nameValidator(name.value)
    const emailError = emailValidator(email.value)
    const phoneError = phoneValidator(phone.value)
    if (emailError || nameError || phoneError) {
      setName({ ...name, error: nameError })
      setEmail({ ...email, error: emailError })
      setPhone({ ...phone, error: phoneError })
    } else {
      fetch(`${endpoint}/user/${id}/${phone.value}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: name.value, email: email.value, password: 0, phone: parseInt(phone.value) })
      })
      .then(() => { navigation.navigate('Home', {id: id}) }).catch()
    }
  }

  return (
    <Background>
          <BackButton goBack={navigation.goBack} />
      <Header>My Information</Header>
      <TextInput
        label="Name"
        returnKeyType="next"
        value={name.value}
        onChangeText={(text) => setName({ value: text, error: '' })}
        error={!!name.error}
        errorText={name.error}
      />
      <TextInput
        label="Phone Number"
        returnKeyType="next"
        value={phone.value}
        onChangeText={(text) => setPhone({ value: text.replace(/[^0-9]/g, ''), error: '' })}
        error={!!phone.error}
        errorText={phone.error}
        autoCompleteType="phone"
        textContentType="number"
        keyboardType= 'numeric'
      />
      <TextInput
        label="Email"
        returnKeyType="next"
        value={email.value}
        onChangeText={(text) => setEmail({ value: text, error: '' })}
        error={!!email.error}
        errorText={email.error}
        autoCapitalize="none"
        autoCompleteType="email"
        textContentType="emailAddress"
        keyboardType="email-address"
          />
      <Button
        mode="contained"
        onPress={onUpdatePressed}
        style={{ marginTop: 24, color: theme.colors.secondary }}
      >
        Update Account
      </Button>
      <View style={styles.row}>
        <Text> Time for a change? </Text>
        <TouchableOpacity onPress={() => navigation.replace('DeleteAccount', {id: id})}>
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
