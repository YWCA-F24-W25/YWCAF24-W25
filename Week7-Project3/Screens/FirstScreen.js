import { Button, StyleSheet, Text, View } from 'react-native';

export default function FirstScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Text>First Screen</Text>
          <Button title='Go To Green' onPress={() => {
              navigation.navigate('secondscreen');
        }}></Button>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'red',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
