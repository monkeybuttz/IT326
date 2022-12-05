import React from 'react'
import { Image, StyleSheet, Text, TouchableOpacity } from 'react-native'
import { theme } from '../../core/theme'



export default function PetButton({navigation}) {
    return (
        <TouchableOpacity style={ styles.button} onPress={() => { navigation.navigate("PetsPage") } }>
            <>
                <Image source={require('../../assets/pets.png')} style={styles.image} />
                <Text style={styles.text} > View Pets </Text>
            </>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    image: {
        width: 150,
        height: 150,
        marginBottom: 0,
    },
    button: {
        marginVertical: 0,
        paddingVertical: 0,
        alignItems: 'center',
        flexDirection: 'column',
    },
    text: {
        color: theme.colors.primary,
        fontWeight: 'bold',
        fontSize: 15,
        lineHeight: 26,
    }
})
