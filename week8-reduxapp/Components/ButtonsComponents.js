

import { StyleSheet, View,Button } from 'react-native';
import { useDispatch } from 'react-redux';
import { decrease, divide, increase, multiplay, setToZero } from '../redux/actions';

export default function ButtonsComponent() {

    const dispatch = useDispatch();

    // how to display the counter in this component
    return (
        <View style={styles.container}>
            <Button title='+ 3' onPress={() => {
                dispatch(increase(3))
                // call functions to update the state (counter) in my redux store via the reducer
             }}/>
            <Button title='- 4' onPress={() => { 
                    dispatch(decrease(4))
            }} />
            <Button title='* 7' onPress={() => {
                dispatch(multiplay(7))
            }} />
            <Button title='\ 2' onPress={() => {
                dispatch(divide(2))
            }} />
            
              <Button title='Set To 0' onPress={() => {
                dispatch(setToZero())
             }}/>
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
});
