import React, { useEffect, useState } from 'react';
import {
  View,
  Text,
  FlatList,
  TouchableOpacity,
  StyleSheet,
  ActivityIndicator,
  LayoutAnimation,
  Platform,
  UIManager,
} from 'react-native';
import Toast from 'react-native-toast-message';
import { Ionicons } from '@expo/vector-icons';

if (Platform.OS === 'android') {
  UIManager.setLayoutAnimationEnabledExperimental &&
    UIManager.setLayoutAnimationEnabledExperimental(true);
}

const BookmarkPage = ({ navigation }) => {
  const [bookmarkedQuestions, setBookmarkedQuestions] = useState([]);
  const [loading, setLoading] = useState(true);
  const studentID = 'Talha';

  useEffect(() => {
    fetchBookmarkedQuestions(studentID);
  }, []);

  const fetchBookmarkedQuestions = async (studentID) => {
    try {
      const backendUrl = `http://192.168.100.8:8080/api/bookmarks/${studentID}`;
      const response = await fetch(backendUrl);
      const data = await response.json();

      if (response.ok) {
        const updatedData = data.map((q) => ({
          ...q,
          userSelection: null,
          submitted: false,
          expanded: false,
        }));
        setBookmarkedQuestions(updatedData);
        if (data.length === 0) {
          Toast.show({ type: 'info', text1: 'No bookmarks found.' });
        }
      } else {
        Toast.show({ type: 'error', text1: data.message || 'Failed to load bookmarks' });
      }
    } catch (error) {
      console.error('Error fetching bookmarks:', error);
      Toast.show({ type: 'error', text1: 'Failed to load bookmarks' });
    } finally {
      setLoading(false);
    }
  };

  const handleOptionSelect = (questionIndex, optionIndex) => {
    const updated = [...bookmarkedQuestions];
    if (!updated[questionIndex].submitted) {
      updated[questionIndex].userSelection = optionIndex;
      setBookmarkedQuestions(updated);
    }
  };

  const handleSubmit = (questionIndex) => {
    const updated = [...bookmarkedQuestions];
    if (updated[questionIndex].userSelection !== null) {
      updated[questionIndex].submitted = true;
      setBookmarkedQuestions(updated);
    } else {
      Toast.show({ type: 'info', text1: 'Please select an option first.' });
    }
  };

  const toggleExpand = (index) => {
    LayoutAnimation.configureNext(LayoutAnimation.Presets.easeInEaseOut);
    const updated = [...bookmarkedQuestions];
    updated[index].expanded = !updated[index].expanded;
    setBookmarkedQuestions(updated);
  };

  const removeBookmark = async (index) => {
    const questionText = bookmarkedQuestions[index].questionText;

    try {
      const backendUrl = `http://192.168.100.8:8080/api/bookmarks/${studentID}/${encodeURIComponent(questionText)}`;
      const response = await fetch(backendUrl, {
        method: 'DELETE',
      });

      if (response.ok) {
        const updated = [...bookmarkedQuestions];
        updated.splice(index, 1);
        setBookmarkedQuestions(updated);
        Toast.show({ type: 'success', text1: 'Bookmark removed successfully.' });
      } else {
        Toast.show({ type: 'error', text1: 'Failed to remove bookmark from server.' });
      }
    } catch (error) {
      console.error('Error deleting bookmark:', error);
      Toast.show({ type: 'error', text1: 'Error removing bookmark.' });
    }
  };

  const renderItem = ({ item, index }) => (
    <TouchableOpacity
      activeOpacity={0.9}
      style={styles.itemContainer}
      onPress={() => toggleExpand(index)}
    >
      <View style={styles.topRow}>
        <Text style={styles.questionText}>{item.questionText}</Text>
        <TouchableOpacity onPress={() => removeBookmark(index)}>
          <Ionicons name="bookmark" size={24} color="#000" />
        </TouchableOpacity>
      </View>

      {item.expanded && item.options && (
        <View style={styles.optionsContainer}>
          {item.options.map((option, i) => {
            const isSelected = item.userSelection === i;
            const isCorrect = item.correctOption === i;
            const isWrong = item.submitted && isSelected && !isCorrect;

            return (
              <View key={i} style={styles.optionWrapper}>
                <TouchableOpacity
                  style={styles.radioContainer}
                  onPress={() => handleOptionSelect(index, i)}
                  disabled={item.submitted}
                >
                  <View style={styles.radioCircle}>
                    {isSelected && <View style={styles.radioDot} />}
                  </View>
                  <Text style={styles.optionText}>{option}</Text>
                  {item.submitted && isCorrect && (
                    <Ionicons name="checkmark-circle" size={20} color="green" style={styles.iconStyle} />
                  )}
                  {isWrong && (
                    <Ionicons name="close-circle" size={20} color="red" style={styles.iconStyle} />
                  )}
                </TouchableOpacity>
              </View>
            );
          })}

          {!item.submitted && (
            <TouchableOpacity
              style={styles.submitButton}
              onPress={() => handleSubmit(index)}
            >
              <Text style={styles.submitText}>Submit</Text>
            </TouchableOpacity>
          )}
        </View>
      )}
    </TouchableOpacity>
  );

  return (
    <View style={styles.container}>
      {/* Header */}
      <View style={styles.header}>
        <TouchableOpacity onPress={() => navigation.goBack()}>
          <Ionicons name="arrow-back" size={24} color="#000" />
        </TouchableOpacity>
        <Text style={styles.headerTitle}>Bookmarks</Text>
        <View style={{ width: 24 }} />
      </View>

      {/* Content */}
      {loading ? (
        <View style={styles.emptyContainer}>
          <ActivityIndicator size="large" color="#4CAF50" />
          <Text style={styles.emptyText}>Loading bookmarks...</Text>
        </View>
      ) : bookmarkedQuestions.length === 0 ? (
        <View style={styles.emptyContainer}>
          <Ionicons name="bookmark-outline" size={80} color="#ccc" />
          <Text style={styles.emptyText}>No bookmarks yet!</Text>
        </View>
      ) : (
        <FlatList
          data={bookmarkedQuestions}
          keyExtractor={(_, i) => i.toString()}
          renderItem={renderItem}
        />
      )}

      <Toast />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    paddingHorizontal: 16,
    paddingTop: 40,
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    marginBottom: 16,
  },
  headerTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#000',
  },
  itemContainer: {
    backgroundColor: '#f4f3f3',
    borderRadius: 12,
    padding: 16,
    marginVertical: 8,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
  },
  topRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  questionText: {
    flex: 1,
    fontSize: 16,
    color: '#333',
    paddingRight: 12,
  },
  optionsContainer: {
    marginTop: 12,
  },
  optionWrapper: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 10,
    justifyContent: 'space-between',
  },
  radioContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    flex: 1,
  },
  optionText: {
    fontSize: 14,
    color: '#555',
  },
  radioCircle: {
    height: 18,
    width: 18,
    borderRadius: 9,
    borderWidth: 2,
    borderColor: '#555',
    alignItems: 'center',
    justifyContent: 'center',
    marginRight: 10,
  },
  radioDot: {
    height: 8,
    width: 8,
    borderRadius: 4,
    backgroundColor: '#555',
  },
  submitButton: {
    marginTop: 12,
    alignSelf: 'flex-start',
    backgroundColor: '#4CAF50',
    paddingHorizontal: 16,
    paddingVertical: 8,
    borderRadius: 8,
  },
  submitText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  emptyContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  emptyText: {
    fontSize: 18,
    color: '#888',
    marginTop: 10,
  },
  iconStyle: {
    marginLeft: 8,
  }
});

export default BookmarkPage;
