import { StyleSheet, Text, View, Image } from 'react-native';
export default function WeatherComponent({ name, icon, temp, description }) { 
   
    return (
   <View style={styles.weatherView}>
        <Text style={styles.textView}>{ name} </Text>
        <Image style={styles.imageView} source={{uri: `https://openweathermap.org/img/wn/${icon}@2x.png`}}></Image>
        <Text style={styles.textView}> { temp} c </Text>
        <Text style={styles.textView}>{description} </Text>
    </View>
   
    );
}
const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'stretch',
      flexDirection:'column',
    justifyContent: 'center',
  },
  map: {
    flex: 1
  },
  
  textView: {
    fontSize: 40
  },
  locationView: {
    flex: 1,
    alignItems: 'center',
    justifyContent:'center'
  },
  weatherView: {
    flex: 1,
     alignItems: 'center',
    justifyContent:'center'
  },
  imageView: {
    width: 100,
    height: 100
  }
});