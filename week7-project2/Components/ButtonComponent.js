import { Button, StyleSheet, View } from 'react-native';
import { useState } from 'react';

export default function ButtonComponent() {
      const [red, setRed] = useState(0);
    return (
        <View style={styles.redview}>
        <Button
            title='+'
            onPress={() => {
                if (red <= 255) {
                    setRed(red + 5);
                }
            }} />
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