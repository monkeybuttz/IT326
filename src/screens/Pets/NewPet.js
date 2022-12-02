import React, { useState } from 'react'
import Background from '../../components/Background'
import Header from '../../components/Header'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { nameValidator } from '../../helpers/nameValidator'
import ImagePicker from '../../components/ExpoImage'
import { View } from 'react-native'
import {endpoint} from '../../../App'

 export default function NewPet({ navigation }) {

     const [name, setName] = useState({ value: '', error: '' })
     const [image, setImage] = useState()
     const [breed, setBreed] = useState({ value: '', error: '' })
     const [notes, setNotes] = useState({ value: '', error: '' })


        const onSavePressed = () => {
            const nameError = nameValidator(name.value)
            if (nameError) {
                setName({ ...name, error: nameError })
                return
            }
            fetch(`${endpoint}/pet`, { method: "POST", body: { ownerId: 2, name: name.value, breed: breed.value, notes: notes.value, image: image } })
                .then()
            navigation.reset({
                index: 0,
                routes: [{ name: 'VerifyEmail' }],
            })
        }
     
     const onUpdatePressed = () => {
                 const nameError = nameValidator(name.value)
            if (nameError) {
                setName({ ...name, error: nameError })
                return
            }
            fetch(`${endpoint}/pet/${2}`, {method: "POST", body: {ownerId: 2, name: name.value, breed: breed.value, notes: notes.value, image: image}})
            navigation.reset({
                index: 0,
                routes: [{ name: 'VerifyEmail' }],
            })
        }
    

        return (
            <Background>
                <BackButton goBack={navigation.goBack} />
                <Header>New Pet</Header>
                <TextInput
                    label="Name"
                    returnKeyType="next"
                    value={name.value}
                    onChangeText={(text) => setName({ value: text, error: '' })}
                    error={!!name.error}
                    errorText={name.error}
                />
                <TextInput
                    label="Breed"
                    returnKeyType="next"
                    value={breed.value}
                    onChangeText={(text) => setName({ value: text, error: '' })}
                    error={!!breed.error}
                    errorText={breed.error}
                />
                <TextInput
                    label="Notes"
                    returnKeyType="next"
                    value={notes.value}
                    onChangeText={(text) => setName({ value: text, error: '' })}
                />
                <View style={{height: 200}}>
                    <ImagePicker title={'Choose Profile Photo'} imageState={[image, setImage]} />
                </View>
                <Button
                    mode="contained"
                    onPress={onSavePressed}
                    style={{ marginTop: 24, color: theme.colors.secondary }}
                >
                    Add Pet
                </Button>
            </Background>
        )
}