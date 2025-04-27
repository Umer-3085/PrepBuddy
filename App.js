// App.js
import React, { useState } from 'react';
import { View, Text, Button, TouchableOpacity, StyleSheet, TextInput } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

const Stack = createNativeStackNavigator();

// Home Screen
function HomeScreen({ navigation }) {
  return (
    <View style={styles.container}>
      <Button title="Show Dashboard" onPress={() => navigation.navigate('Dashboard')} />
    </View>
  );
}

// Dashboard Screen
function DashboardScreen({ navigation }) {
  return (
    <View style={styles.dashboardContainer}>
      <Text style={styles.title}>Teacher Dashboard</Text>

      {/* Add Questions Card */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>Add Questions</Text>
        <Text style={styles.cardText}>Add new multiple-choice questions to the database.</Text>
        <TouchableOpacity 
          style={styles.button}
          onPress={() => navigation.navigate('AddQuestion')}
        >
          <Text style={styles.buttonText}>Add Now</Text>
        </TouchableOpacity>
      </View>

      {/* Remove Questions Card */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>Remove Questions</Text>
        <Text style={styles.cardText}>Remove questions from the database.</Text>
        <TouchableOpacity 
          style={styles.button}
          onPress={() => navigation.navigate('RemoveQuestion')}
        >
          <Text style={styles.buttonText}>Remove Now</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

// Add Question Screen
function AddQuestionScreen({ navigation }) {
  const [formData, setFormData] = useState({
    subject: '',
    topic: '',
    difficulty: '',
    question: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    answer: ''
  });

  const handleAddQuestion = () => {
    console.log('New Question:', {
      ...formData,
      options: [formData.option1, formData.option2, formData.option3, formData.option4]
    });
    navigation.goBack();
  };

  return (
    <View style={styles.formContainer}>
      <Text style={styles.formTitle}>Add New Question</Text>

      <TextInput
        style={styles.input}
        placeholder="Subject"
        value={formData.subject}
        onChangeText={(text) => setFormData({ ...formData, subject: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Topic"
        value={formData.topic}
        onChangeText={(text) => setFormData({ ...formData, topic: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Difficulty"
        value={formData.difficulty}
        onChangeText={(text) => setFormData({ ...formData, difficulty: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Question Statement"
        multiline
        value={formData.question}
        onChangeText={(text) => setFormData({ ...formData, question: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Option 1"
        value={formData.option1}
        onChangeText={(text) => setFormData({ ...formData, option1: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Option 2"
        value={formData.option2}
        onChangeText={(text) => setFormData({ ...formData, option2: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Option 3"
        value={formData.option3}
        onChangeText={(text) => setFormData({ ...formData, option3: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Option 4"
        value={formData.option4}
        onChangeText={(text) => setFormData({ ...formData, option4: text })}
      />
      <TextInput
        style={styles.input}
        placeholder="Correct Answer (Option number)"
        value={formData.answer}
        onChangeText={(text) => setFormData({ ...formData, answer: text })}
      />
      
      <TouchableOpacity style={styles.button} onPress={handleAddQuestion}>
        <Text style={styles.buttonText}>Add Question</Text>
      </TouchableOpacity>
    </View>
  );
}

// Remove Question Screen
function RemoveQuestionScreen({ navigation }) {
  const [removeCriteria, setRemoveCriteria] = useState({
    subject: '',
    topic: '',
    searchQuery: ''
  });

  const handleRemove = () => {
    console.log('Removal Criteria:', removeCriteria);
    navigation.goBack();
  };

  return (
    <View style={styles.formContainer}>
      <Text style={styles.formTitle}>Remove Questions</Text>

      <TextInput
        style={styles.input}
        placeholder="Subject"
        value={removeCriteria.subject}
        onChangeText={(text) => setRemoveCriteria({ ...removeCriteria, subject: text })}
      />

      <TextInput
        style={styles.input}
        placeholder="Topic"
        value={removeCriteria.topic}
        onChangeText={(text) => setRemoveCriteria({ ...removeCriteria, topic: text })}
      />

      <TextInput
        style={styles.input}
        placeholder="🔍 Search Questions"
        value={removeCriteria.searchQuery}
        onChangeText={(text) => setRemoveCriteria({ ...removeCriteria, searchQuery: text })}
      />

      <TouchableOpacity 
        style={styles.button} 
        onPress={handleRemove}
      >
        <Text style={styles.buttonText}>Remove Questions</Text>
      </TouchableOpacity>
    </View>
  );
}

// Main App Component
export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Home">
        <Stack.Screen name="Home" component={HomeScreen} />
        <Stack.Screen 
          name="Dashboard" 
          component={DashboardScreen}
          options={{ title: 'PrepBuddy' }} 
        />
        <Stack.Screen 
          name="AddQuestion" 
          component={AddQuestionScreen}
          options={{ title: 'Add Question' }}
        />
        <Stack.Screen 
          name="RemoveQuestion" 
          component={RemoveQuestionScreen}
          options={{ title: 'Remove Questions' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

// Styles
const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  dashboardContainer: {
    flex: 1,
    paddingTop: 30,
    alignItems: 'center',
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  card: {
    width: '90%',
    backgroundColor: '#fff',
    padding: 20,
    marginVertical: 10,
    borderRadius: 10,
    elevation: 3,
  },
  cardTitle: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  cardText: {
    marginVertical: 10,
  },
  button: {
    backgroundColor: '#000',
    padding: 15,
    borderRadius: 5,
    alignItems: 'center',
    marginTop: 10,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
  },
  formContainer: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  formTitle: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 20,
    textAlign: 'center',
  },
  input: {
    backgroundColor: 'white',
    padding: 15,
    borderRadius: 10,
    marginBottom: 15,
    fontSize: 16,
    elevation: 2,
  },
});