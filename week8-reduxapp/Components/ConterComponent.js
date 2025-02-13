

import { StyleSheet, View,Text } from 'react-native';
import { useSelector } from 'react-redux';


export default function CounterComponent({ name }) {

    const mystateValue = useSelector(store => store)
    
    // how to display the counter in this component
    return (
        <View style={styles.container}>
            <Text style={styles.textstyle}>{name} { mystateValue }</Text>
            
        </View>
    );
}



const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'red',
    alignItems: 'stretch',
    justifyContent: 'center',
    },
    textstyle: {
        fontSize: 30
    }
});
