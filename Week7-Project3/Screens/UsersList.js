import { Image, FlatList, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import React, { useState, useEffect } from 'react';
import SearchBar from '../Components/SearchBar';


export default function UsersList() {
  const [users, setUsers] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchUsers = async () => { 
    fetch('https://randomuser.me/api?results=20').
      then(stringresponse => stringresponse.json()).
      then(jsondata => { 
        setUsers(jsondata.results);
      }).
      catch(console.error());
  }

  useEffect(() => {
    fetchUsers()
  }, []);

  const filterUsers = (filterTerm) => { 
    if (filterTerm == '') {
      console.log("No search")
      fetchUsers()
    }
    else { 
       console.log(filterTerm)
      var resultList = users.filter((user) => {
        return user.name.title == filterTerm
     })
      if (resultList.length > 0) { 
        setUsers(resultList)
      }
    }
  }

   var userRow = (user) => 
     <TouchableOpacity>
       <View style={styles.rowStyle}>
         <Image source={{uri : user.picture.thumbnail }} style={styles.imageStyle} ></Image>
          <View>
           <Text> {user.name.title} {user.name.first} {user.name.last} </Text>
              <Text> { user.email}</Text>
         </View>
         </View>
    </TouchableOpacity>

  return (
    <View style={styles.container}>
      <SearchBar
        term={searchTerm}
        onTermChange={(newterm) => { 
          setSearchTerm(newterm)
          filterUsers(newterm)
      }}/>
      <FlatList
        data={users}
        keyExtractor={(item,i) => i}
        renderItem={(listItem)=> userRow(listItem.item)}
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
    borderWidth: 2,
    borderRadius: 10,
    margin: 5
  },
  imageStyle: {
    width: 50,
    height: 50
  }
});
