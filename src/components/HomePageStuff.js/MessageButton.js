import React from 'react'
import { Image, StyleSheet, Text, TouchableOpacity } from 'react-native'
import { theme } from '../../core/theme'



export default function MessageButton({id, navigation}) {
    return (
        <TouchableOpacity style={styles.button} onPress={() => { navigation.navigate("Inbox", {id: id}) } }>
            <>
                <Image source={require('../../assets/mail.png')} style={styles.image} />
                <Text style={styles.text} > Open Inbox</Text>
            </>
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    image: {
        width: 130,
        height: 90,
        margin: 6,
    },
    button: {
        marginVertical: 0,
        paddingVertical: 25,
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
