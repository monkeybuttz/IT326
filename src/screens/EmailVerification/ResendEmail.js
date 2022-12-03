import React, { useState } from 'react'
import { StyleSheet, View } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { emailValidator } from '../../helpers/emailValidator'
import { theme } from '../../core/theme'
import endpoint from '../../helpers/endpoint'


export default function ResendEmail({ navigation, accountID }) {

    const [email, setEmail] = useState({ value: '', error: '' })

    const onVerifyPressed = () => {
        const emailError = emailValidator(email.value)
        if (emailError) {
            setEmail({ ...email, error: emailError })
            return
        }
        fetch(`${endpoint}/email`)
        navigation.reset({
            index: 0,
            routes: [{ name: 'VerifyEmail' }],
        })
    }

  return (
    <Background>
      <BackButton goBack={navigation.navigate('LoginScreen')} />
      <Logo />
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
      <Button mode="contained" onPress={onVerifyPressed}>
        Resend
      </Button>
      <View style={styles.row}>
        <Text>This will update the email attached to your account.</Text>
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
