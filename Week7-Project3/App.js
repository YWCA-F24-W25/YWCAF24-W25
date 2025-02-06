
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';

import FirstScreen from './Screens/FirstScreen';
import SecondScreen from './Screens/SecondScreen';
import NamesListScreen from './Screens/NamesListScreen';
import AddingNewNameScreen from './Screens/AddingNewNameScreen';
import EditingNameScreen from './Screens/EditingNameScreen';
export default function App() {

  var stack = createNativeStackNavigator();
  const tab = createBottomTabNavigator();

  // State Manegment Packet ===> Redux 
  // Redux Store == one place to have all state variables across your app.
  
  const namesStack = () => {
    return ( 
          <stack.Navigator>
             <stack.Screen name='namesList' component={NamesListScreen}></stack.Screen>
             <stack.Screen name='addNewName' component={AddingNewNameScreen}></stack.Screen>
             <stack.Screen name='editName' component={EditingNameScreen}></stack.Screen>
      </stack.Navigator> 
    );
  }
  return (
    <NavigationContainer>
      <tab.Navigator>
        <tab.Screen name='names' component={namesStack}/>
        <tab.Screen name='Red' component={FirstScreen} />
        <tab.Screen name='Green'  component={SecondScreen} />
      </tab.Navigator>
       
      </NavigationContainer>
  );
}
