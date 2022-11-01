import React, { useState } from 'react'
import { FlatList, SafeAreaView, View, Image, StyleSheet, Text, TouchableOpacity } from 'react-native';
import Background from '../../components/Background'
import Logo from '../../components/Logo'
import Header from '../../components/Header'
import { theme } from '../../core/theme';
import BackButton from '../../components/BackButton';

export default function PetsPage({ navigation }) {

  const [pets, setPets] = useState([{ src: '../../../assets/pets.png', name: "Susan" }, {}, {}, {}]);

  const style = StyleSheet.create({
    container: {
      flex: 1,
      borderRadius: 9999,
    },
    smallImageThumbnail: {
      backgroundColor: "black",
      justifyContent: 'center',
      alignItems: 'center',
      height: 110,
      width: 110,
      margin: 10,
      borderRadius: 9999,
      borderStyle: "solid",
      borderWidth: 5,
      borderColor: "white"
    },
    smallButton: {
      backgroundColor: "#f2dcdc",
      margin: 10,
      height: 150,
      width: 130,
      borderRadius: 20,
    },
    imageThumbnail: {
      backgroundColor: "black",
      justifyContent: 'center',
      alignItems: 'center',
      height: 200,
      width: 200,
      marginHorizontal: 35,
      marginVertical: 20,
      borderRadius: 9999,
      borderStyle: "solid",
      borderWidth: 5,
      borderColor: "white"
    },
    button: {
      backgroundColor: "#f2dcdc",
      margin: 10,
      height: 275,
      width: 275,
      borderRadius: 20,
    }
  });

  return (
    <Background>
      <BackButton/>
      <Header>GroomBuddy</Header>
      <SafeAreaView style={style.container}>
      <FlatList
        data={pets}
          renderItem={({ item }) => (
            <TouchableOpacity style={pets.length > 6 ? style.smallButton : style.button}>
              <View style={{ flex: 1, flexDirection: 'column', margin: 1, }}>
                <Image style={pets.length > 6 ? style.smallImageThumbnail : style.imageThumbnail} source={item.src} />
                <Text>{item.name}</Text>
            </View>
          </TouchableOpacity>
        )}
        //Setting the number of column
        numColumns={pets.length > 6 ? 2 : 1}
        keyExtractor={(item, index) => index}
      />
    </SafeAreaView>
    </Background>
  )
}

