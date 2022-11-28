import React, { useState } from 'react';
import { Image, View, TouchableOpacity } from 'react-native';
import * as ImagePicker from 'expo-image-picker';
import Button from './Button';


export default function ExpoImagePicker({title, imageState}) {
  const [image, setImage] = imageState;

    const pickImage = async () => {
    // No permissions request is necessary for launching the image library
    let result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.All,
      allowsEditing: true,
      aspect: [4, 3],
      quality: 1,
    });

    if (!result.cancelled) {
      setImage(result.uri);
    }
  };

  return (
    <View style={{ flex: 1, alignItems: 'center', justifyContent: 'center', height:200 }}>
      {!image ? <Button onPress={pickImage}>
              {title}
      </Button>
       : <TouchableOpacity
        onPress={pickImage}>{image && <Image source={{ uri: image }} style={{ width: 200, height: 200 }} />}
      </TouchableOpacity>}
    </View>
  );
}