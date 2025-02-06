import { StyleSheet, View , Button, TextInput} from 'react-native';
import React, { useState } from "react";

export default function AddingNewNameScreen({ navigation , route }) {
    const [newName, setNewName] = useState('');
    

    const addNewName = () => {
        const { addNewNameFunction } = route.params;
        if (newName.trim != '') {
             addNewNameFunction(newName);
            navigation.goBack();
        }
    }

    return (
    <View style={styles.container}>
          <TextInput
            value= {newName}
              placeholder='Enter New Name'
              onChangeText={(v) => {
                  setNewName(v);
              }}
          />
            <Button title='Save And Back' onPress={addNewName}></Button>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
