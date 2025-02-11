import { StyleSheet, Text, View, FlatList, TouchableOpacity ,Alert} from 'react-native';
import React , {useEffect, useState} from 'react';
import * as SQLite from 'expo-sqlite';
import Feather from '@expo/vector-icons/Feather';


export default function FavoriteCitiesScreens({ route }) { 

  const [list, setList] = useState([]);

    const getALlCitiesFromDB = async () => { 
      var dblist = []
            // open the db and run one query ==> select * from City
        const db = await SQLite.openDatabaseAsync('cities-db.db');
      const allRows = await db.getAllAsync('SELECT * FROM City');
          for (const row of allRows) {
            dblist.push(row.city)  
          }
      setList(dblist);
    }
  
  const deleteLogic = async (cityToDelete) => { 
    const db = await SQLite.openDatabaseAsync('cities-db.db');
    await db.runAsync("Delete from City where city = $c", { $c: cityToDelete });
    getALlCitiesFromDB()
  }
  
     const deleteAlert = (todeleteCity) => {
        console.log(todeleteCity)
       Alert.alert('Delete From DB?', 'Are you sure you want to delete this city from yourFavorite Cities? ',
         [
           {
             text: 'Yes',
             onPress: () => {
              deleteLogic(todeleteCity)
             }
           },
           {
             text: 'No',
             onPress: () => {
               
             }
           }
         ]);
      }
  
  
  const deleteOneCity = async (todeleteCity) => { 
      deleteAlert(todeleteCity)
  }
  
  useEffect(() => {
    getALlCitiesFromDB();
  }, []);
   var cityRow = (city) => 
       <View style={styles.rowStyle}>
       <Text> {city} </Text>
       <TouchableOpacity onPress={() => {
         deleteOneCity(city)
       }}>
             <Feather name="trash-2" style={styles.IconStyle} />
        </TouchableOpacity>
      </View>
    
  return (
    <View style={styles.container}>
      <FlatList
        data={list}
        keyExtractor={(item,i) => i}
        renderItem={(listItem)=> cityRow(listItem.item)}
      ></FlatList>
         
    </View>
  )
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
    IconStyle: {
        fontSize: 35,
        color: 'black',
    },
 rowStyle: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    padding: 10,
    borderWidth: 1,
    borderRadius: 10,
    margin: 5
  },

});