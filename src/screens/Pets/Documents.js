import React, { useState, useEffect } from 'react'
import BackButton from '../../components/BackButton'
import Background from '../../components/Background'
import { View, FlatList } from 'react-native'
import Header from '../../components/Header'
import Button from '../../components/Button'
import ImagePicker from '../../components/ExpoImage'
import { getStatusBarHeight } from 'react-native-status-bar-height'

export default function Documents({ navigation, route, options  }) {

    const [groomer, setGroomer] = useState({name: "", id:-1 });
    const [photos, setPhotos] = useState([]);
    const [photo, setPhoto] = useState();

    useEffect(()=>{}, [groomer])

    const save = () => {
        
    }

  return (
      <Background>
          <BackButton goBack={navigation.goBack} />
          <View style={{height: getStatusBarHeight()+30}}/>
          <Header>Upload Vet Document Photos</Header>
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
