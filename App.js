import React from 'react'
import { Provider } from 'react-native-paper'
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import { theme } from './src/core/theme'
import {StartScreen, LoginScreen, RegisterScreen, ResetPasswordScreen,
  VerifyEmail, ResendEmail, DeleteAccount, Home, PetsPage,
  EditProfile, Inbox, Pet, NewPet, NewAppointment, Documents,
} from './src/screens'

const Stack = createStackNavigator()

export const endpoint = "https://1715-138-87-133-12.ngrok.io"

export default function App() {
  return (
    <Provider theme={theme}>
      <NavigationContainer>
        <Stack.Navigator
          initialRouteName={"Login"}
          screenOptions={{
            headerShown: false,
          }}
        >

          <Stack.Screen name="StartScreen" component={StartScreen} />


          <Stack.Screen name="LoginScreen" component={LoginScreen} />
          <Stack.Screen name="RegisterScreen" component={RegisterScreen} />

          <Stack.Screen name="VerifyEmail" component={VerifyEmail} />
          <Stack.Screen name="ResendEmail" component={ResendEmail} />

          <Stack.Screen name="Home" component={Home}/>

          <Stack.Screen name="Profile" component={EditProfile} />
          <Stack.Screen name="DeleteAccount" component={DeleteAccount} />

          
          <Stack.Screen name="PetsPage" component={PetsPage} />
          <Stack.Screen name="Pet" component={Pet} />
          <Stack.Screen name="NewPet" component={NewPet} />
          <Stack.Screen name="Documents" component={Documents} />

          <Stack.Screen name="NewAppointment" component={NewAppointment} />

          <Stack.Screen name="Inbox" component={Inbox} />

          <Stack.Screen name="ResetPasswordScreen" component={ResetPasswordScreen} />
          
          


        </Stack.Navigator>
      </NavigationContainer>
    </Provider>
  )
}
