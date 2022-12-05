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
import endpoint from '../../helpers/endpoint'


export default function RegisterScreen({ navigation }) {
  const [name, setName] = useState({ value: '', error: '' })
  const [userName, setUserName] = useState({ value: '', error: '' })
  const [phone, setPhone] = useState({ value: '', error: '' })
  const [email, setEmail] = useState({ value: '', error: '' })
  const [password, setPassword] = useState({ value: '', error: '' })
  const [isGroomer, setIsGroomer] = useState(0);

  const [resp, setResp] = useState('');


  const onSignUpPressed = () => {
    const nameError = nameValidator(name.value)
     const userNameError = nameValidator(userName.value)
    const emailError = emailValidator(email.value)
    const passwordError = passwordValidator(password.value)
    const phoneError = phoneValidator(phone.value)
    if (emailError || passwordError || nameError || phoneError || userNameError) {
      setName({ ...name, error: nameError })
      setUserName({...userName, error: userNameError ? "username can't be empty" : ''})
      setEmail({ ...email, error: emailError })
      setPassword({ ...password, error: passwordError })
      setPhone({ ...phone, error: phoneError })
      return
    }
    fetch(`${endpoint}/user`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: -1,
        name: name.value,
        username: userName.value,
        email: email.value,
        password: password.value,
        phoneNumber: parseInt(phone.value),
        isGroomer: isGroomer
      })
    }).then(res => res.json()).then(res => {
      if(res?.id){
        navigation.navigate('Home', {...res})
      }
    })
  }

  return (
    <Background>
      <Text>{ JSON.stringify(resp)}</Text>
      <BackButton goBack={navigation.goBack} />
      <Header>Create Account</Header>
      <View  style={{
        flexDirection: "row",
        alignItems: "",
        width: '100%'
      }}>
        <Button onPress={() => setIsGroomer(0)} style={{width: '50%'}} mode={`${!isGroomer && "contained"}`}>Pet Parent</Button>
        <Button onPress={() => setIsGroomer(1)} style={{width: '50%'}} mode={`${isGroomer && "contained"}`}>Groomer</Button>
      </View>
      <TextInput
        label="Name"
        returnKeyType="next"
        value={name.value}
        onChangeText={(text) => setName({ value: text, error: '' })}
        error={!!name.error}
        errorText={name.error}
      />
      <TextInput
        label="Username"
        returnKeyType="next"
        value={userName.value}
        onChangeText={(text) => setUserName({ value: text, error: '' })}
        error={!!userName.error}
        errorText={userName.error}
      />
      <TextInput
        label="Phone Number"
        returnKeyType="next"
        value={phone.value}
        onChangeText={(text) => setPhone({ value: text.replace(/[^0-9]/g, ''), error: '' })}
        error={!!phone.error}
        errorText={phone.error}

        autoCompleteType="phone"
        textContentType="phoneNumber"
        keyboardType="phone-number"
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
      <TextInput
        label="Password"
        returnKeyType="done"
        value={password.value}
        onChangeText={(text) => setPassword({ value: text, error: '' })}
        error={!!password.error}
        errorText={password.error}
        secureTextEntry
      />
      <Button
        mode="contained"
        onPress={onSignUpPressed}
        style={{ marginTop: 24 }}
      >
        Sign Up
      </Button>
      <View style={styles.row}>
        <Text>Already have an account? </Text>
        <TouchableOpacity onPress={() => navigation.replace('LoginScreen')}>
          <Text style={styles.link}>Login</Text>
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
