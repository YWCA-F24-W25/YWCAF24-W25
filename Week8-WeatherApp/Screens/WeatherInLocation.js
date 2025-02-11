import { StyleSheet, View,Text } from 'react-native';
import React , {useEffect, useState} from 'react';
import WeatherCompnent from '../Components/WeatherComponent';
import * as Location from 'expo-location';


export default function WeatherInLocation({ route }) { 
    const [latitude, setLat] = useState(0);
    const [longitude, setLog] = useState(0);
     const [temp, setTemp] = useState(0)
    const [description, setDescription] = useState("")
    const [icon,SetIcon] = useState("")
    const [name, setName] = useState("")
  
    useEffect(() => {
    
        const fetchWeather = async (lat,lon) => { 
      fetch( `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=071c3ffca10be01d334505630d2c1a9c&units=metric`).
      then(stringresponse => stringresponse.json()).
      then(json => { 
              setName(json.nam)
          setDescription(json.weather[0].description)
          setTemp(json.main.temp)
          SetIcon(json.weather[0].icon)

      })
  }
    async function getCurrentLocation() {
    
      let { status } = await Location.requestForegroundPermissionsAsync();
      if (status !== 'granted') {
        setErrorMsg('Permission to access location was denied');
        return;
      }
        //let location = await Location.getCurrentPositionAsync({});
        
        await Location.watchPositionAsync({
            accuracy: Location.Accuracy.BestForNavigation,
            distanceInterval: 10,
            timeInterval: 1000
        }, (location) => { 
          console.log("New Location Found")
          setLat(location.coords.latitude)
          setLog(location.coords.longitude)
         
               console.log(location.coords.latitude);
               console.log(location.coords.longitude);
               fetchWeather(location.coords.latitude, location.coords.longitude)
        })
        
    }

    getCurrentLocation();
  }, []);


    return (
      <View style={styles.weatherView}>
        <Text style={styles.textView}>latitude: {latitude}</Text>
         <Text  style={styles.textView}>longitude: { longitude}</Text>
        <WeatherCompnent
          name={name}
          icon={icon}
          temp={temp}
          description={description} /> 
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
    fontSize: 30
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