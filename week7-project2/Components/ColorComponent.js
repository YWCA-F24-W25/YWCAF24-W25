// import
// function
// style 

import { useState } from 'react';
import { Button, StyleSheet , View,Text} from 'react-native';
export default function ColorComponent() {

    const [red, setRed] = useState(0);
    const [green, setGreen] = useState(0);
    const [blue, setBlue] = useState(0);

    return (
        <View style={styles.container}>
            <View style={{
                width: 150,
                height: 150,
                backgroundColor: `rgb(${red},${green},${blue})`
            }} ></View>
            <Text> {red} - {green} - { blue}</Text>
            <Button style={styles.redbutton}
                title='+Red'
                onPress={() => {   
                    setRed(red + 5);
            }}></Button>
            <Button title='+Green' onPress={() => { 
                setGreen(green + 5);
            }}></Button>
            <Button title='+Blue' onPress={() => { 
                setBlue(blue + 5);
            }}></Button>
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
    redbutton: {
        backgroundColor: 'red',
        fontSize: 20
    }

});