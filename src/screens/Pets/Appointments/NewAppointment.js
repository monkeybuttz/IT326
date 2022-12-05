import React, { useState, useRef, useEffect } from 'react'
import BackButton from '../../../components/BackButton'
import Background from '../../../components/Background'
import { View, Text, FlatList, Touchable } from 'react-native'
import TimePicker from './DateTimePicker'
import TextInput from '../../../components/TextInput'
import Header from '../../../components/Header'
import Button from '../../../components/Button'
import SearchableDropdown from 'react-native-searchable-dropdown';
import ImagePicker from '../../../components/ExpoImage'
import { theme } from '../../../core/theme'
import { getStatusBarHeight } from 'react-native-status-bar-height'
import { NEWDATE } from 'mysql/lib/protocol/constants/types'

import endpoint from '../../../helpers/endpoint'
import { TouchableOpacity } from 'react-native-gesture-handler'

export default function NewAppointment({ route, navigation }) {

  const { id } = route.params;
    const [notes, setNotes] = useState();
    const [address, setAddress] = useState();
    const [groomer, setGroomer] = useState({name: "", id:-1 });
    const [groomers, setGroomers] = useState([{ name: "barbra", id: 3 }, { name: "natilie", id: 3 }]);
    const [photos, setPhotos] = useState([]);
    const [photo, setPhoto] = useState();
    const [datePicker, setDatePicker] = useState(new Date());

  useEffect(() => {
    if (id) {
      fetch(`${endpoint}/groomApt/${id}`)
        .then(res => res.json())
        .then(data =>{
          setNotes(data.notes)
          setAddress(data.location)
          //setPhotos(data.images)
          setDatePicker(new Date(data.date))
          fetch(`${endpoint}/user/${data.groomerId}`)
            .then(res2 => res2.json())
            .then(data2 =>{
              setGroomer(data2)
          })
        })
    }
  }, [])
  
  useEffect(() => {
    if (groomer.name.length > 0 && groomer.id == -1) {
      fetch(`${endpoint}/searchForGroomer/${groomer.name}`)
      .then(res => { const resp = res.json(); if (resp) { return resp } else { return []} })
      .then(data => setGroomers([...data])
      );
    }
  }, [groomer])

  const save = () => {
        fetch(`${endpoint}/groomApt${id ? "/" + id : ""}`,  {
        method: (id) ? 'PUT' : 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
          body: JSON.stringify({notes: notes, address: address, groomerID: groomer.id, photos: photos, date: datePicker.toLocaleDateString("en-US") }),
    })
    }

  return (
    <Background>
          <BackButton goBack={navigation.goBack} />
          <View style={{height: getStatusBarHeight()+30}}/>
          <View style={{height: 30}}>
              <TimePicker dateState={[datePicker, setDatePicker]}/>
      </View>
      <View style={{ width: '100%'}}>
          <TextInput
                label="Groomer"
                returnKeyType="next"
                value={groomer.name}
          onChangeText={(text) => setGroomer({name:text, id: -1})} 
        />
        {(groomer.id == -1 && groomer.name?.length > 0) && <View>
          {groomers.map(g => {
            return <TouchableOpacity style={{ width: '100%', fontSize: 13, borderColor: theme.colors.secondary,
              borderWidth: 1, padding: 12, borderRadius: 4
            }}
              onPress={() => setGroomer(g)}
            >
          <Text  style={{ width: '100%', fontSize: 15, }}>{g.name}</Text>
        </TouchableOpacity>
        })}
        </View>}
      </View>
            <TextInput
                label="Address"
                returnKeyType="next"
                value={address}
                onChangeText={(text) => setAddress(text)}
          />
          <TextInput
            label="Notes"
            returnKeyType="next"
            value={notes}
            onChangeText={(text) => setNotes(text)}
          />
          <Header>Refrence Photos</Header>
          <FlatList
                data={photos}
                renderItem={({ item, index }) => (
                <View style={{ height: 200 }}>
                    <ImagePicker title={'Add Refrence Photo'}
                        imageState={[item, (newP) => {
                            setPhotos(photos.map((p, i) => i == index? newP : p ))
                        }]}
                    />
                </View>
            )}
            //Setting the number of column
            numColumns={1}
            keyExtractor={(item, index) => index}
          />
          <View style={{ height: 75 }}>
              <ImagePicker title={'Add Refrence Photo'} imageState={[photo, (newP) => {
                    setPhotos([...photos, newP])
                }]} />
            </View>
          <Button
              mode="contained"
            onPress={() => save('LoginScreen')}
          >
              Save
          </Button>
    </Background>
  )
}

