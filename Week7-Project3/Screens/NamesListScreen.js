import { StyleSheet, Text, View , Button, FlatList, TouchableOpacity} from 'react-native';
import Ionicons from '@expo/vector-icons/Ionicons';
import React, { useState } from "react";

export default function NamesListScreen({ navigation }) {
    var [names, setNames] =  useState(['John', 'Mary', 'Lee', 'George']);
    return ( 
    <View style={styles.container}>
          <FlatList
              data={names}
            // keyExtractor={(item, i) => i.toString()}
                renderItem={({ item }) => {
                    return (
                        <TouchableOpacity onPress={() => {
                            navigation.navigate('editName',
                                {
                                    selectedName: item,
                                    updatingNameFunction: (updatedName) => { 
                                        var i = names.indexOf(item);
                                         names[i] = updatedName;
                                         setNames((prevItesm) => [...prevItesm]);
                                       // setNames(names.map ((item,i) => (i === index? item : updatedName)))
                                    }
                                });
                        }}>
                            <Text style={styles.textStyle}>{item}</Text>
                        </TouchableOpacity>
                    );
              }}         
            ></FlatList>
          <TouchableOpacity
              style={styles.floatingActionButton}
              onPress={() => {
                  navigation.navigate('addNewName',{
                      addNewNameFunction:
                          (newName) => { 
                          setNames((prevItesm) => [...prevItesm, newName]
                          );
                      }
                  });
            }} >
                    <Ionicons 
                        name="add"
                        size={50}
                        color="black" />
              </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'while',
    alignItems: 'flex-start',
    justifyContent: 'center',
    },
    textStyle : {
        fontSize: 20
    },
    floatingActionButton: {
        alignSelf: 'flex-end'
    }
});
