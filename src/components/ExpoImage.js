import React, { useState } from 'react';
import { Image, View, TouchableOpacity } from 'react-native';
import * as ImagePicker from 'react-native-image-picker';
import Button from './Button';


export default function ExpoImagePicker({title, imageState}) {
  const [image, setImage] = imageState;
  const [imageUri, setImageUri] = useState('');

const options = {
   title: 'Select Image',
   customButtons: [
     {
       name: 'customOptionKey',
       title: 'Choose Photo from Custom Option'
     },
   ],
   storageOptions: {
     skipBackup: true,
     path: 'images',
   },
};

    const pickImage = async () => {
   ImagePicker.showImagePicker(options, response => {
   console.log('Response = ', response);
   if (response.didCancel) {
     console.log('User cancelled image picker');
   } else if (response.error) {
     console.log('ImagePicker Error: ', response.error);
   } else if (response.customButton) {
     console.log(
       'User tapped custom button: ',
       response.customButton
     );
     alert(response.customButton);
   } else {
     setFilePath(response);
   }
});
    };
  
  const [status, requestPermission] = ImagePicker.useCameraPermissions();

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