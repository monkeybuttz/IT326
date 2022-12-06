import React, { useEffect, useState } from 'react'
import { FlatList, SafeAreaView, View, Image, StyleSheet, Text, TouchableOpacity, TouchableWithoutFeedback } from 'react-native';
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton';
import TextInput from '../../components/TextInput'
import endpoint from '../../helpers/endpoint'


export default function PetsPage({ navigation, route }) {

  const [pets, setPets] = useState([]);
  const [search, setSearch] = useState('');
  // const reload = () => {
  //     fetch(`${endpoint}/pets/${userID}`, { method: 'GET' }
  //     ).then((res) => { return res.json() }).then(data => setPets([...data,  ...[{ petID: 0, name: "New Pet", src: "../../../assets/pets.png", link: "NewPet" }]])).catch()
  //   };

  const { userID } = route.params;
  // navigation.addListener('willFocus', reload());

  useEffect(() => {
      fetch(`${endpoint}/pets/${userID}`, { method: 'GET' }
      ).then((res) => { return res.json() }).then(data => setPets([...data,  ...[{ petID: 0, name: "New Pet", src: "../../../assets/pets.png", link: "NewPet" }]])).catch()
    },[]);

    const style = StyleSheet.create({
      container: {
        flex: 1,
        borderRadius: 9999,
      },
      imageThumbnail: {
        backgroundColor: "white",
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
        <View style={{paddingTop: 16}}></View>
        <BackButton goBack={navigation.goBack} />
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
              (!search || item?.name?.startsWith(search) || item?.name == 'New Pet') && <TouchableOpacity style={style.button}
                onPress={() => { navigation.navigate(`${item.link ? item.link : 'Pet'}`, {petID: item.petID, userID }) }}>
                <View style={{ flex: 1, flexDirection: 'column', margin: 1, }}>
                  <Image style={style.imageThumbnail} source={{uri: item.image}} />
                  <Text>{item.name}</Text>
                </View>
              </TouchableOpacity>
            )}
            //Setting the number of column
            numColumns={1}
            keyExtractor={(item, index) => index}
          />
        </SafeAreaView>
      </Background>
    )
  }
