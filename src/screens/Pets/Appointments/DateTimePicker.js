import React, { useState } from 'react'
import Background from '../../../components/Background'
import { Button, SafeAreaView, StyleSheet, Text, View } from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';

export default function TimePicker({ dateState }) {

  const [datePicker, setDatePicker] = useState(false);
 
  const [date, setDate] = dateState;

  function onDateSelected(event, value) {
    setDate(value);
    setDatePicker(false);
  };
 
  function onTimeSelected(event, value) {
    setTime(value);
    setTimePicker(false);
    };
    
const styleSheet = StyleSheet.create({
 
  MainContainer: {
        alignItems: '',
    backgroundColor: 'white',
        flex: 1,
        flexDirection: 'row',
        alignItems: "center"
  },
  // Style for iOS ONLY...
  datePicker: {
    justifyContent: 'center',
    alignItems: 'flex-start',
    width: 130,
    height: 45,
    display: 'flex',
  },
 
});

  return (
    <View style={styleSheet.MainContainer}>
        <Text>Date:</Text>
          <DateTimePicker
            value={date}
            mode={'date'}
            is24Hour={true}
            onChange={onDateSelected}
            style={styleSheet.datePicker}
          />
    </View>
  )
}

