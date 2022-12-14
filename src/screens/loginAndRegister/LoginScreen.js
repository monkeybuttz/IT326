import React, { useState, useEffect } from 'react'
import { TouchableOpacity, StyleSheet, View } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Header from '../../components/Header'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { emailValidator } from '../../helpers/emailValidator'
import { passwordValidator } from '../../helpers/passwordValidator'
import endpoint from '../../helpers/endpoint'

export default function LoginScreen({ navigation }) {
  const [email, setEmail] = useState({ value: '', error: '' })
  const [password, setPassword] = useState({ value: '', error: '' })
  const [invalid, setInvalid] = useState(false);

  const onLoginPressed = () => {
    const passwordError = passwordValidator(password.value)
    if ( passwordError) {
      setPassword({ ...password, error: passwordError })
      return
    }
        fetch(`${endpoint}/user/login`, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
        },
          body: JSON.stringify({ login: email.value, password: password.value })
        }).then(res => { setInvalid(true); return res.json() }).catch((e) => setInvalid(true))
          .then((res) => {
        if (res.id){
          navigation.navigate('Home', {id: res.id, name: res.name})
        } else {
          setInvalid(true);
      }
      }).catch()
  }

  return (
    <Background>
      <BackButton goBack={navigation.goBack} />
      <Logo />
      <Header>Welcome back.</Header>
      <TextInput
        label="Username or Email"
        returnKeyType="next"
        value={email.value}
        onChangeText={(text) => { setInvalid(false); setEmail({ value: text, error: '' })}}
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
        onChangeText={(text) => {setInvalid(false); setPassword({ value: text, error: '' })}}
        error={!!password.error}
        errorText={password.error}
        secureTextEntry
      />
      <Button style={{marginTop: 20}} mode="contained" onPress={onLoginPressed}>
        Login
      </Button>
      {invalid && <Text style={{fontWeight: 'bold', color: theme.colors.primary}}> Your email and password do not match</Text>}
      <View style={styles.row}>
        <Text>Don???t have an account? </Text>
        <TouchableOpacity onPress={() => navigation.replace('RegisterScreen')}>
          <Text style={styles.link}>Sign up</Text>
        </TouchableOpacity>
      </View>
    </Background>
  )
}

const styles = StyleSheet.create({
  forgotPassword: {
    width: '100%',
    alignItems: 'flex-end',
    marginBottom: 24,
  },
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  forgot: {
    fontSize: 13,
    color: theme.colors.secondary,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
})
