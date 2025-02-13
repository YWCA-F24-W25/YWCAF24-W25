import { StyleSheet, View } from 'react-native';
import { Provider } from 'react-redux';
import CounterComponent from './Components/ConterComponent';
import ButtonsComponent from './Components/ButtonsComponents';
import  counterStore  from './redux/CounterStore';

export default function App() {
  return (

    <Provider store={counterStore}>
      <View style={styles.container}>
        
          <CounterComponent name="Component A" />
          <ButtonsComponent/>
          <CounterComponent name="Component B"/>

        </View>
      </Provider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
