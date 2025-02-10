import { useEffect, useState } from 'react';
import { StyleSheet, Text, View , Button} from 'react-native';

export default function QuoteScreen() {

  const [author, setAuthor] = useState('');
  const [category, setcategory] = useState('');
  const [quote, setquote] = useState('');

const url = 'https://quotes-by-api-ninjas.p.rapidapi.com/v1/quotes';
const options = {
	method: 'GET',
	headers: {
		'x-rapidapi-key': 'cee3bd1e22msheedb98e87dec740p196316jsn6d22f5917037',
		'x-rapidapi-host': 'quotes-by-api-ninjas.p.rapidapi.com'
	}
};

  const fetchNewQuote = async () => {
    try {
      // const response = await fetch(url, options);
      // const result = await response.json();
      // console.log(result);

      fetch(url, options).
        then(response => response.json()).
        then(jsonData => {
          setAuthor(jsonData[0].author);
          setcategory(jsonData[0].category);
          setquote(jsonData[0].quote);
      }).catch(error => {
        console.log(error)
      })
    } catch (error) {
      console.error(error);
    }
  }

  useEffect(() => {
    fetchNewQuote();
  },[])


  return (
    <View style={styles.container}>
    <Text style={styles.textTitleStyle}> {category} quote </Text>
      
      <Text style={ styles.textQouteStyle}>{ quote}</Text>
     
      <Text style={styles.textAuthorStyle}>Author { author}</Text>
          <Button title='Get A New Quote' onPress={() => {
        fetchNewQuote();
        }}></Button>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'white',
    alignItems: 'center',
    justifyContent: 'center',
  },
  textQouteStyle: {
    fontSize: 30
  },
   textAuthorStyle: {
     fontSize: 20,
     fontWeight: 'bold'
  },
     textTitleStyle: {
     fontSize: 40,
  }
});
