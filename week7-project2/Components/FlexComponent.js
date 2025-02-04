
import {  StyleSheet , View} from 'react-native';


export default function FlexComponent() {
    return (
        <View style = {styles.container}>
            <View style={{ flex: 2, backgroundColor : 'red', width: 100, height : 100}}></View>
            <View style={{ flex : 2, backgroundColor : 'blue' , width: 100, height : 100}}></View>
            <View style={{ flex: 1, backgroundColor : 'green', width: 100, height : 100}}></View>
        </View>

    );
}
const styles = StyleSheet.create({ // css
    container: {
        backgroundColor: 'gray',
        flexDirection: 'column',// flexbox for container
        flex: 1,
        justifyContent: 'space-around',
        alignItems: 'center'
    }
});