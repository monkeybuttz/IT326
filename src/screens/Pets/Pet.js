import React, { useState, useEffect } from 'react'
import { View, StyleSheet, Image, TouchableOpacity, FlatList } from 'react-native'
import { Text } from 'react-native-paper'
import Button from '../../components/Button'
import Background from '../../components/Background'
import Header from '../../components/Header'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { getStatusBarHeight } from 'react-native-status-bar-height'
import endpoint from '../../helpers/endpoint'


export default function Pet({ route, navigation }) {


  const { petID, userId }  = route.params;
  const [pet, setPet] = useState({ src: '../../../assets/pets.png', name: "Allie", breed: "Pitbull", notes: "Submissive and breedable.",  groomApts: [{date: (new Date()).toDateString(), id: 1}] });

  useEffect(() => { 
      fetch(`${endpoint}/pet/${parseInt(petID)}`, { method: 'GET' }
      ).then((res) => { return res.json() }).then(data => setPet(data)).catch()
  }, []);

  
  const favoriteGroom = (item) => {
    fetch(`${endpoint}/groomingapt/${item.aptId}`, { method: 'POST' }
    ).then(
        setPet({...pet,  groomApts: pet. groomApts.map(ap => {
          if (ap.id == item.aptId) {
          return ({...item, favorite: !item.favorited})
          } else {
            return ap
          }
        })
      }) )
  }

const style = StyleSheet.create({
    container: {
      flex: 1,
      borderRadius: 9999,
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
    container: {
      position: 'absolute',
      top: 10 + getStatusBarHeight(),
      right: 4,
  },
  favorite: {
    fontSize: 21,
    fontWeight: 'bold',
    paddingVertical: 12,
    }
});

  return (
    <Background>
      <BackButton goBack={navigation.goBack} />
      <TouchableOpacity onPress={() => { navigation.navigate('NewPet', { id: petID, userId: userId })}} style={styles.container}>
        <Text style={{color: theme.colors.primary, fontSize: 18}}>Edit</Text>
    </TouchableOpacity>
      <View style={{marginTop: getStatusBarHeight()}}>
        <Header> {pet.name} </Header>
      </View>
          <Image style={style.imageThumbnail} source={pet.src} />
      <View>
        <Text>Breed: {pet.breed} </Text>
        <Text>Notes: {pet.notes} </Text> 
      </View>
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end",
        width: '100%'
      }}>
        <Button
                mode="contained"
                onPress={() => { navigation.navigate("NewAppointment", {id: 0, petId: petID, userId: userId })}}
          style={{ marginLeft: 6, width: "100%", marginRight: 10, marginTop: 24, color: theme.colors.secondary }}
              >
                New Appointment
        </Button>
      </View>
      <View style={{
        flexDirection: "row",
        alignItems: "flex-end",
        width: '100%'
      }}>
        <Button
          mode="contained"
          onPress={() => { }}
          style={{width: "50%", marginTop: 24, marginRight: 10, color: theme.colors.secondary }}
        >
         💾 Profile
        </Button>
        <Button
        mode="contained"
          onPress={() => { navigation.navigate('Documents', {petID: petID})}}
        style={{width: "50%", marginTop: 24, color: theme.colors.secondary }}
      >
        View Docs
      </Button>
      </View>
      
      <FlatList
        data={pet. groomApts}
          renderItem={({ item }) => (
              <View style={{height: 'auto', marginTop: 10, paddingBottom:10, width: 300, borderColor: 'black', borderWidth: 1, paddingHorizontal: 20 }} >
                <View style={{flex: 1, flexDirection: 'row', margin: 1, alignItems: "center", justifyContent: 'space-between' }}>
                <Text style={{ margin: 4, width: 190 }}>
                  {item.date}
                  </Text>
                <TouchableOpacity
                  onPress={() => {favoriteGroom(item)}}>
                  <Text style={{...style.favorite, color: item.favorited ? theme.colors.secondary : 'black'}}>{item.favorited ? '★' : '☆'}</Text>
                </TouchableOpacity>
              </View>
              <View style={{ flex: 1, flexDirection: 'row', margin: 1, justifyContent: 'space-between' }}>
                    <TouchableOpacity onPress={() => {
                      navigation.navigate('NewAppointment', {id: item.aptId, petId: petID, userId: userId })
                    }}>
                      <Text>View and Edit</Text>
                    </TouchableOpacity>
                <TouchableOpacity onPress={() => {
                  fetch(`${endpoint}/groomingapt/delete/${item.aptId}`).then(() =>
                    setPet({...pet,  groomApts: pet. groomApts.filter(ap => {
                      ap.id != item.aptId
                  })}))}}>
                      <Text> Delete </Text>
                    </TouchableOpacity>
                </View>
              </View>
        )}
        //Setting the number of column
        numColumns={1}
        keyExtractor={(item, index) => index}
      />
    </Background>
  )
}

const styles = StyleSheet.create({
  container: {
    position: 'absolute',
    top: 10 + getStatusBarHeight(),
    right: 4,
  }
})
