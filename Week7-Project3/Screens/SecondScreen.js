import { StyleSheet, Text, View , Button} from 'react-native';

export default function SecondScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text>Second Screen</Text>
          <Button title='Go Back' onPress={() => {
              navigation.pop();
        }}></Button>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'green',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
