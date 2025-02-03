// import section
import { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';

// function/component section
export default function App() {
  // Three usecases for JavaScript
  // 1- provid the needed logic
  // 2- provide the UI design
  // 3- provide the style for UI component
  var welcomeMeg = "Hello React Native - First Project"
  var [number, setNumber] = useState(95);
  var newElement = <View style={styles.innerView}>
    <Text>Text In View</Text>
      </View>
  return ( // JSX object. ----- similar to HTML
    <View style={styles.container}>
      <Text>{ welcomeMeg }</Text>
      <Text style={styles.textStyle}>Hello React Native </Text>
      <Text style={styles.textStyle}> { number }</Text>
      <Button title="Counter++" onPress={() => {
        setNumber(number + 1);// a re-render process will happened 
            // any logic to run after clicking this button
        }}></Button>
      <Button title="Counter--" onPress={() => { 
         setNumber(number - 1);//a re-render process will happened 
      }}></Button>
      <TextInput
        placeholder="Enter Your email"
        style = {styles.textInputStyle}
      > </TextInput>   
      {newElement}
   
    </View>
  );
}
// style section---------------similar to CSS
const styles = StyleSheet.create({
   textStyle: {
    fontSize : 40
  },
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  textInputStyle: {
    fontSize: 30,
    color : '#333'
  },
  innerView: {
    backgroundColor : '#f00'

  }
 

});
