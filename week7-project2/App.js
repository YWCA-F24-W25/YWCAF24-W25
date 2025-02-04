import { StyleSheet, View } from 'react-native';
import TextComponent from './Components/TextComponent';
import ColorComponent from './Components/ColorComponent';
import FlexComponent from './Components/FlexComponent';

export default function App() {
  return (// html + xml == jsx 
    <View style={styles.container}>
      {/* <FlexComponent/> */}

      <TextComponent />
      <ColorComponent />
    </View>
  );
}

const styles = StyleSheet.create({ // css
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
