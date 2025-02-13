import { FlatList, StyleSheet, Text, TouchableOpacity, View , Alert} from 'react-native';
import React, { useState , useEffect} from 'react';
import SearchBar from '../Components/SearchBar';
import Feather from '@expo/vector-icons/Feather';
import * as SQLite from 'expo-sqlite';

// basic state managment 
// redux for advace state managment 

export default function CitiesList({navigation}) {
  const [cities, setCities] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [db, setDB] = useState(null);

  const initDB = async () => {
    try {
    const db = await SQLite.openDatabaseAsync('cities-db.db');
    setDB(db);
      console.log('db connected ');
    await db.execAsync(`
CREATE TABLE IF NOT EXISTS City (id INTEGER PRIMARY KEY NOT NULL, city TEXT NOT NULL);`);
 console.log('table created/connected');
      }catch (error) {
        console.error(error)
      }
  };

  const insertNewCity = async (cityname) => {
    const statement = await db.prepareAsync(`INSERT INTO City (city) VALUES ($value);`);
    try {
      let result = await statement.executeAsync({ $value: cityname });
      console.log('city inserted to db');
      
    }finally {
    await statement.finalizeAsync();
}
  }

    const alertFunction = (selectedCity) => {
      console.log(selectedCity)
      Alert.alert('Save to DB?','Do you want to save this city to Favorite Cities? ',
        [
        {
          text: 'Yes',
            onPress: () => {
              console.log(selectedCity)
              insertNewCity(selectedCity)
             
              navigation.navigate('weatherScreen', { selectedCity: selectedCity })
          }
        },
        {
          text: 'No',
          onPress: () => {
              console.log(selectedCity)
              navigation.navigate('weatherScreen', { selectedCity: selectedCity })

          }
        }
      ])
    }
  useEffect(()=>{
    initDB();
    
    },[])

  const fetchCities = async (searchValue) => { 
    fetch(`http://gd.geobytes.com/AutoCompleteCity?&q=${searchValue}`).
      then(stringresponse => stringresponse.json()).
      then(jsondata => { 
        setCities(jsondata);
      })
  }

   var cityRow = (city) => 
     <TouchableOpacity
    onPress={() => {
      alertFunction(city)
       
     }}>
       <View style={styles.rowStyle}>
          <View>
           <Text> {city} </Text>
           
         </View>
         </View>
    </TouchableOpacity>

  return (
    <View style={styles.container}>
      <View style={styles.rowViewStyle}>
        <TouchableOpacity onPress={() => {
          navigation.navigate('LocationScreen')
        }}>
           <Feather name="navigation" style={ styles.IconStyle} />
        </TouchableOpacity>
        <SearchBar
        term={searchTerm}
        onTermChange={(newterm) => { 
          setSearchTerm(newterm)
          if (newterm.length > 2){
              fetchCities(newterm)
          }
          if (newterm == ''){
            setCities([])
          }
          }} />
        <TouchableOpacity onPress={() => {
          navigation.navigate('FavoriteCities')
        }}>
           <Feather name="star" style={ styles.IconStyle} />
        </TouchableOpacity>
      </View>
      {
       cities.length > 0 ?
      <FlatList
        data={cities}
        keyExtractor={(item,i) => i}
        renderItem={(listItem)=> cityRow(listItem.item)}
      ></FlatList>
      :
      <Text>Start Seaching</Text>
      }
         
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
     alignItems: 'stretch',
    justifyContent: 'flex-start'
  },
  rowStyle: {
    flexDirection: 'row',
    alignItems: 'stretch',
    justifyContent: 'space-around',
    padding: 10,
    borderWidth: 1,
    borderRadius: 10,
    margin: 5
  },
  imageStyle: {
    width: 50,
    height: 50
  },
   IconStyle: {
        fontSize: 35,
        color: 'black',
    },
  rowViewStyle: {
    flexDirection: 'row',
    alignItems:'center'
  }
});
