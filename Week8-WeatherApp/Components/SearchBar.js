import { StyleSheet, View , TextInput} from 'react-native';
import React from "react";
import Feather from '@expo/vector-icons/Feather';
export default function SearchBar({ term, onTermChange}) {

    // JSX object ===> HTML 
    return (
        <View style={styles.container}>
            <Feather name="search" style={ styles.IconStyle} />
            <TextInput 
                style= {styles.textInputStyle}
                value={term}
                onChangeText={onTermChange}
                placeholder='Search For Cities'
            ></TextInput>
        </View>
    );


}


const styles = StyleSheet.create({
  container: {
    borderWidth: 1,
        borderColor: 'black',
        padding: 5,
        margin: 5,
        flexDirection: 'row',
        alignItems: 'center',
        flex: 1
    },
    IconStyle: {
        fontSize: 35,
        color: 'black',
    },
    textInputStyle: {
        fontSize: 20, 
        margin: 10,
    }
});