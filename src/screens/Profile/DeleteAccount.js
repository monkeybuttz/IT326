import React from 'react'
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Header from '../../components/Header'
import Button from '../../components/Button'
import Paragraph from '../../components/Paragraph'
import { theme } from '../../core/theme'
import endpoint from '../../helpers/endpoint'



export default function DeleteAccount({ navigation }) {

  const deleteAccount = () => {
    useEffect(() => {
      fetch(`${endpoint}/user/${id}`, { method: 'DELETE' })
      .then(() => { navigation.navigate('LoginScreen') }).catch()
    }, []);
    }

  return (
    <Background>
      <Logo />
          <Header style={{text: "center"} }>Are you sure you want to say Goodbye?</Header>
          <Paragraph>This can not be undone.</Paragraph>
      <Button
        mode="contained"
        onPress={deleteAccount}
      >
        deleteAccount
      </Button>
          <Button
              color={theme.colors.secondary}
        mode="outlined"
        onPress={() => { navigation.goBack() }}
      >
        Go Back
      </Button>
    </Background>
  )
}