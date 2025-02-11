import { StyleSheet, Text, View, Image } from 'react-native';
import React , {useEffect, useState} from 'react';
import WeatherCompnent from '../Components/WeatherComponent';
export default function WeatherScreen({ route }) { 
    
    const { selectedCity } = route.params;
    const [temp, setTemp] = useState(0)
    const [description, setDescription] = useState("")
    const [icon,SetIcon] = useState("")
    const [name, setName] = useState("")
  
    const fetchWeather = async () => { 
    fetch(`https://api.openweathermap.org/data/2.5/weather?q=${selectedCity}&appid=071c3ffca10be01d334505630d2c1a9c&units=metric`).
      then(stringresponse => stringresponse.json()).
      then(json => { 
         setName(json.name)
          setDescription(json.weather[0].description)
          setTemp(json.main.temp)
          SetIcon(json.weather[0].icon)

      })
  }

  useEffect(()=>{
    fetchWeather()
  })
    return (
      <View style={styles.weatherView}>
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