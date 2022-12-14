import React, { useEffect, useState } from 'react'
import Background from '../../components/Background'
import Header from '../../components/Header'
import Button from '../../components/Button'
import TextInput from '../../components/TextInput'
import BackButton from '../../components/BackButton'
import { theme } from '../../core/theme'
import { nameValidator } from '../../helpers/nameValidator'
import ImagePicker from '../../components/ExpoImage'
import { View,  StyleSheet, Text, TouchableOpacity } from 'react-native'
import endpoint from '../../helpers/endpoint'


export default function NewPet({ navigation, route }) {

    const [name, setName] = useState({ value: '', error: '' })
    const [image, setImage] = useState()
    const [breed, setBreed] = useState({ value: '', error: '' })
    const [notes, setNotes] = useState({ value: '', error: '' })

    const { id, userID } = route.params;
     
    useEffect(() => {
        if (id) {
            fetch(`${endpoint}/pet/${parseInt(id)}`).then(res => res.json())
                .then(data => {
                    setImage(data.image);
                    setBreed({ value: data.breed, error: '' });
                    setName({ value: data.name, error: '' });
                    setNotes({ value: data.notes, error: '' });
                }
                )
        }
    }, [])

    const onUpdatePressed = async () => {
        const nameError = nameValidator(name.value)
        if (nameError) {
            setName({ ...name, error: nameError })
            return
        }
        fetch(`${endpoint}/pet/${id ? parseInt(id) : "add/" + userID}`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                petID: parseInt(id),
                ownerID: parseInt(userID),
                name: name.value, breed: breed.value,
                notes: notes.value, image: image
            })
        }).then(navigation.goBack());
    }
    

    return (
        <Background>
            <BackButton goBack={navigation.goBack} />
            <Header>{id ? "Edit" : "New Pet"}</Header>
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
                onChangeText={(text) => setBreed({ value: text, error: '' })}
                error={!!breed.error}
                errorText={breed.error}
            />
            <TextInput
                label="Notes"
                returnKeyType="next"
                value={notes.value}
                onChangeText={(text) => setNotes({ value: text, error: '' })}
            />
            <View style={{ height: 200 }}>
                <ImagePicker title={'Choose Profile Photo'} imageState={[image, setImage]} />
            </View>
            <Button
                mode="contained"
                onPress={onUpdatePressed}
                style={{ marginTop: 24, color: theme.colors.secondary }}
            >
                {id ? "Update Pet" : "Add Pet"}
            </Button>
            {id && <View style={styles.row}>
                <Text> Do you want to </Text>
                <TouchableOpacity onPress={() => fetch(`${endpoint}/pet/delete/${parseInt(id)}`).then(navigation.navigate('Home', { id: userID }))}>
                    <Text style={styles.link}>Delete Pet</Text>
                </TouchableOpacity>
            </View>}
        </Background>
    )
}

const styles = StyleSheet.create({
  row: {
    flexDirection: 'row',
    marginTop: 4,
  },
  link: {
    fontWeight: 'bold',
    color: theme.colors.primary,
  },
})