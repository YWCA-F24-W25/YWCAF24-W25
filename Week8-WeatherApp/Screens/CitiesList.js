import { FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import React, { useState } from 'react';
import SearchBar from '../Components/SearchBar';

// basic state managment 
// redux for advace state managment 

export default function CitiesList({navigation}) {
  const [cities, setCities] = useState(['']);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchCities = async (searchValue) => { 
    fetch(`http://gd.geobytes.com/AutoCompleteCity?&q=${searchValue}`).
      then(stringresponse => stringresponse.json()).
      then(jsondata => { 
        setCities(jsondata);
      })
  }

   var cityRow = (city) => 
     <TouchableOpacity onPress={() => {
       navigation.navigate('weatherScreen', { selectedCity: city })
     }}>
       <View style={styles.rowStyle}>
          <View>
           <Text> {city} </Text>
           
         </View>
         </View>
    </TouchableOpacity>

  return (
    <View style={styles.container}>
      <SearchBar
        term={searchTerm}
        onTermChange={(newterm) => { 
          setSearchTerm(newterm)
          if (newterm.length > 2){
              fetchCities(newterm)
          }
          if (newterm == ''){
            setCities([''])
          }
      }}/>
      <FlatList
        data={cities}
        keyExtractor={(item,i) => i}
        renderItem={(listItem)=> cityRow(listItem.item)}
      ></FlatList>
         
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
  }
});
