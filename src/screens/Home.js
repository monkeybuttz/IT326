import React, { useEffect, useState } from 'react'
import { View, Text } from 'react-native'
import Background from '../components/Background'
import Logo from '../components/Logo'
import { theme } from '../core/theme'
import SettingsButton from '../components/SettingsButton'
import PetButton from '../components/HomePageStuff.js/PetButton'
import ProfileButton from '../components/HomePageStuff.js/ProfileButton'
import MessageButton from '../components/HomePageStuff.js/MessageButton'

export default function Home({ navigation }) {

  const [greeting, setGreeting] = useState('');

  useEffect(() => {
    fetch("http://localhost:8080/api/greeting", {
      method: "GET",
      parameters: JSON.stringify({greeting: "goodbye"}),
      body: JSON.stringify({greeting: "goodbye"}),
    }).then(async res => {
    setGreeting(await res.text())
 console.warn('Reached:', greeting)})},[])

  const style = {
    header: {
      position: 'absolute',
      top: 55,
      left: 4,
      color: theme.colors.primary,
      fontWeight: 'bold',
      fontSize: 18,
      lineHeight: 26,
    }
  }

  return (
    <Background>
      <SettingsButton />
      <Text style={style.header}>{greeting}Welcome Back</Text>
      <Logo />
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end"
      }}>
        <PetButton navigation={navigation}/>
        <ProfileButton navigation={navigation} />
      </View>
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end"
      }}>
        <MessageButton navigation={navigation} />
      </View>
    </Background>
  )
}
