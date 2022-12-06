import React, { useEffect, useState } from 'react'
import { View, Text } from 'react-native'
import Background from '../components/Background'
import Logo from '../components/Logo'
import { theme } from '../core/theme'
import SettingsButton from '../components/SettingsButton'
import PetButton from '../components/HomePageStuff.js/PetButton'
import ProfileButton from '../components/HomePageStuff.js/ProfileButton'
import MessageButton from '../components/HomePageStuff.js/MessageButton'

export default function Home({ navigation, route }) {

    const { id,  }  = route.params;

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
      <Text style={style.header}>Welcome Back</Text>
      <Logo />
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end"
      }}>
        <PetButton id={id} navigation={navigation}/>
        <ProfileButton id={ id} navigation={navigation} />
      </View>
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end"
      }}>
        <MessageButton id={ id} navigation={navigation} />
      </View>
    </Background>
  )
}
