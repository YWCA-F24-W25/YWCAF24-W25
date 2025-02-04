import { useState } from 'react';
import { StyleSheet, Text, TextInput, View ,Image, TouchableOpacity} from 'react-native';

export default function TextComponent() {
    var [userInput, setUserInput] = useState('');

    const imageClicked = () => {
        alert(`You clicked on the image`);
    }

    return (// JSX
        <View style={ styles.container}>
            <Text style={styles.textstyle}>This is my text component</Text>
            <TextInput
                style={styles.textstyle}
                placeholder='Enter Your Name'
                value={userInput}
                onChangeText={(v) => {
                    setUserInput(v);
                }}
            ></TextInput>
            <Text style={styles.blacktextstyle} >Welcome to our class {userInput}</Text>
            
            <TouchableOpacity onPress={imageClicked}>
                <Image style={ styles.imageStyle} source={require("../assets/image.png") }/>
            </TouchableOpacity>
        </View>
    );
}
const styles = StyleSheet.create({ // css
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    },
    textstyle: {
        fontSize: 20,
        fontWeight: 'bold',
        color: 'red'
    },
     blacktextstyle: {
        fontSize: 20,
        color: 'black'
    }, 
    imageStyle: {
        width: 200,
        height: 200

    }
});
