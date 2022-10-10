import React, { useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
// https://github.com/oblador/react-native-vector-icons/issues/328#issuecomment-860038108
import FAIcon from 'react-native-vector-icons/FontAwesome';
import IOIcon from 'react-native-vector-icons/Ionicons';

import Reader from './Reader';

const Stack = createNativeStackNavigator();

export default function App() {
  const [isReady, setIsReady] = useState(false);

  useEffect(() => {
    FAIcon.loadFont()
      .then(() => IOIcon.loadFont())
      .then(() => setIsReady(true));
  }, []);

  if (!isReady) {
    return null;
  }

  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Reader">
        <Stack.Screen name="Reader" component={Reader} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
