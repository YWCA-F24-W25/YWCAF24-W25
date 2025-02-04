// import
// function
// style 

import { useState } from 'react';
import { Button, StyleSheet, View, Text } from 'react-native';
export default function ColorComponent() {

    const [red, setRed] = useState(0);
    const [green, setGreen] = useState(0);
    const [blue, setBlue] = useState(0);
    // update this app and add three more buttons to subtract the color value
    // 5 - 6 mins
    return (
        <View style={styles.container}>
            <View style={{
                width: 150,
                height: 150,
                backgroundColor: `rgb(${red},${green},${blue})`
            }} ></View>
            <Text> {red} - {green} - {blue}</Text>
            
            <View style={styles.rowviewstyle}>
                  
                 <View style={styles.redview}>
                        <Button
                            title='+'
                            onPress={() => {
                                if (red <= 255) {
                                    setRed(red + 5);
                                }
                            }} />
                        </View>

                <View style={styles.greeview}>
                    <Button title='+' onPress={() => { 
                        if (green <= 255) {
                            setGreen(green + 5);
                        }
                    }}/>
                </View>
                
                    <Button title='+Blue' onPress={() => { 
                        if (blue <= 255) {
                            setBlue(blue + 5);
                        }
                }}></Button>
            </View>
            <View style={styles.rowviewstyle}>
                 <View style={styles.redview}>
                        <Button
                            title='-'
                            onPress={() => {  
                                if (red > 0) {
                                    setRed(red - 5);
                                }
                        }}></Button>
                </View>
                <View style={styles.greeview}>
                        <Button title='-' onPress={() => { 
                            if (green > 0) {
                                setGreen(green - 5);
                            }
                         }}/>
                </View>
                        <Button title='Blue' onPress={() => {
                            if (blue > 0) {
                                setBlue(blue - 5);
                            }
                            }}></Button>
                </View>
        </View>
    );

}

const styles = StyleSheet.create({ // css
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
    },
    redbutton: {
        backgroundColor: 'red',
        color: 'red',
        fontSize: 20
    },
    rowviewstyle: {
        flexDirection: 'row'
    },
    redview: {
        backgroundColor: 'red'
    },
  greeview: {
        backgroundColor: 'green'
    },
  blueview: {
        backgroundColor: 'blue'
    }

});