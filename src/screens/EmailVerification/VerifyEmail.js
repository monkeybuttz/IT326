import React, { useState } from 'react'
import { TouchableOpacity, StyleSheet, View } from 'react-native'
import { Text } from 'react-native-paper'
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'

export default function VerifyEmail({ navigation, accountID }) {

    const [code, setCode] = useState('');

    const onVerifyPressed = () => {
      navigation.navigate('Home');
    }

  return (
    <Background>
      <BackButton goBack={navigation.navigate('Login')} />
      <Logo />
        <TextInput
        label="Verification Code"
        returnKeyType="next"
        value={code}
        onChangeText={(text) => setCode({ value: text, error: '' })}
        autoCapitalize="none"
      />
      <Button mode="contained" onPress={onVerifyPressed}>
        Verfiy
      </Button>
      <View style={styles.row}>
        <Text>Don't have a code? </Text>
        <TouchableOpacity onPress={() => navigation.replace('ResendEmail')}>
          <Text style={styles.link}>Resend email</Text>
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
