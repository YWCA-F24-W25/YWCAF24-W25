import { StyleSheet, View,Text } from 'react-native';
import React , {useEffect, useState} from 'react';
import WeatherCompnent from '../Components/WeatherComponent';
import * as Location from 'expo-location';
import MapView, {Marker} from 'react-native-maps';



export default function WeatherInLocation() { 
    const [latitude, setLat] = useState(0);
    const [longitude, setLog] = useState(0);
     const [temp, setTemp] = useState(0)
    const [description, setDescription] = useState("")
    const [icon,SetIcon] = useState("")
    const [name, setName] = useState("");

  const [region, setRegion] = useState({
    latitude: 37.78825, 
    longitude: -122.4324, 
    latitudeDelta: 0.0922, 
    longitudeDelta: 0.0421 
  });

  const handleRegionChange = (newRegion) => {
   
    setRegion(newRegion); // Update state with new region if needed
  };

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
         handleRegionChange({
                latitude: location.coords.latitude, 
                longitude: location.coords.longitude, 
                 latitudeDelta: 0.0922, 
                 longitudeDelta: 0.0421 
               })
              
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
        <MapView
          style={styles.map}
          region={region} 
          onRegionChange={handleRegionChange} 
        >
          <Marker
            coordinate={{latitude: latitude, longitude: longitude}}
            title={"my location"}
/>
        </MapView>
        
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
  },
    map: {
    width: '100%',
    height: '50%',
  },
});