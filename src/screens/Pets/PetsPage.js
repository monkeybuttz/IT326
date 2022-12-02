import React, { useEffect, useState } from 'react'
import { FlatList, SafeAreaView, View, Image, StyleSheet, Text, TouchableOpacity, TouchableWithoutFeedback } from 'react-native';
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton';
import TextInput from '../../components/TextInput'
import { endpoint } from '../../../App'

export default function PetsPage({ navigation }) {

  const [pets, setPets] = useState([{ src: '../../../assets/pets.png', name: "Allie" }, {}, {}, {name: "New Pet", src: "../../../assets/pets.png", link: "NewPet"}]);
  const [search, setSearch] = useState('');


  useEffect(() => { 
    useEffect(() => {
      fetch(`${endpoint}/user/pets/${userId}`, { method: 'GET' }
      ).then((res) => { return res.json() }).then(data => setPets(data)).catch()
    }, []);

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
      <BackButton goBack={navigation.goBack}/>
      <Header>GroomBuddy</Header>
      <TextInput
        label="Search"
        returnKeyType="next"
        value={search.value}
        onChangeText={(text) => setSearch(text)}
      />
      <SafeAreaView style={style.container}>
      <FlatList
        data={pets}
          renderItem={({ item }) => (
            (!search || item?.name?.startsWith(search) || item?.name == 'New Pet') && <TouchableOpacity style={pets.length > 6 ? style.smallButton : style.button}
              onPress={()=>{navigation.navigate(`${item.link? item.link :'Pet'}`)}}>
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

