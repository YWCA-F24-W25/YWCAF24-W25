import { StyleSheet} from 'react-native';
import CitiesList from './Screens/CitiesList';
import WeatherScreen from './Screens/WeatherScreen';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import FavoriteCitiesScreens from './Screens/FavoriteCitiesScreen';
import WeatherInLocation from './Screens/WeatherInLocation';

export default function App() {

  var stack = createNativeStackNavigator();

  return (
    <NavigationContainer>
       <stack.Navigator>
             <stack.Screen name='citiesList' component={CitiesList}></stack.Screen>
             <stack.Screen name='weatherScreen' component={WeatherScreen}></stack.Screen>
             <stack.Screen name='FavoriteCities' component={FavoriteCitiesScreens}></stack.Screen>
             <stack.Screen name='LocationScreen' component={WeatherInLocation}></stack.Screen>

      </stack.Navigator> 
       
      </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'stretch',
    justifyContent: 'flex-start',
    paddingTop: 40
  },
});
