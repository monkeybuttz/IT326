import React, { useState, useRef, useEffect } from 'react'
import BackButton from '../../../components/BackButton'
import Background from '../../../components/Background'
import { View, Text, FlatList } from 'react-native'
import TimePicker from './DateTimePicker'
import TextInput from '../../../components/TextInput'
import Header from '../../../components/Header'
import Button from '../../../components/Button'
import SearchableDropdown from 'react-native-searchable-dropdown';
import ImagePicker from '../../../components/ExpoImage'
import { theme } from '../../../core/theme'
import { getStatusBarHeight } from 'react-native-status-bar-height'

export default function NewAppointment({ navigation, route, options  }) {

    const [notes, setNotes] = useState();
    const [address, setAddress] = useState();
    const [groomer, setGroomer] = useState({name: "", id:-1 });
    const [groomers, serGroomers] = useState([{ name: "barbra", id: 3 }, { name: "natilie", id: 3 }]);
    const [photos, setPhotos] = useState([]);
    const [photo, setPhoto] = useState();

    useEffect(()=>{}, [groomer])

    const save = () => {
        
    }

  return (
      <Background>
          <BackButton goBack={navigation.goBack} />
          <View style={{height: getStatusBarHeight()+30}}/>
          <View style={{height: 30}}>
              <TimePicker />
          </View>
          <SearchableDropdown
            onItemSelect={(groomer) => {
              setGroomer( groomer );
            }}
            containerStyle={{width: '100%',
              marginVertical: 12,  padding: 5,
              borderWidth: 1, borderRadius: 4,}}
            itemStyle={{
              backgroundColor: theme.colors.surface,
              padding: 10,
              marginTop: 2,
              borderColor: '',
              borderWidth: 1,
              borderRadius: 5,
            }}
            itemTextStyle={{ }}
            itemsContainerStyle={{ maxHeight: 140 }}
            items={groomers}
            defaultIndex={2}
            resetValue={false}
            textInputProps={
                {
                    onChangeText: (text) => { setGroomer({name: text, id: -1})},
                value: groomer.name,
                placeholder: "Search for Groomer",
                underlineColorAndroid: "transparent",
                style: {
                    padding: 12,
                    fontSize: 16,

                },
              }
            }
            listProps={
              {
                nestedScrollEnabled: true,
              }
            }
        />
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

