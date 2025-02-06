import { useState } from 'react';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';

export default function EditingNameScreen({ navigation, route }) {
    

    const name = route.params.selectedName;
    //const { selectedName } = route.params;

    const { updatingNameFunction } = route.params;
    
    const [updatedName, setUpdatedName] = useState(name);

  return (
    <View style={styles.container}>
        
          <TextInput 
              style= {styles.textStyle}
              value={updatedName}
              onChangeText={(v) => {
                  setUpdatedName(v)
               }}
          ></TextInput> 
          <Button title='Update and Back ' onPress={() => {
              updatingNameFunction(updatedName);
              navigation.goBack();
        }}></Button>
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
    textStyle: {fontSize: 30}
});
