import React, { useEffect, useState } from 'react';
import { TouchableOpacity, ActivityIndicator, ToastAndroid } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';
import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/bookmarks';

const BookmarkButton = ({ questionText, options, correctOption }) => {
  const [isBookmarked, setIsBookmarked] = useState(false);
  const [loading, setLoading] = useState(true);
  const studentId = 'Talha'; // Hardcoded for testing

  // Check if the question is already bookmarked
  useEffect(() => {
    const checkBookmark = async () => {
      try {
        const res = await axios.get(`${BASE_URL}/${studentId}`);
        const matched = res.data.find(
          (b) => b.questionText === questionText
        );
        setIsBookmarked(!!matched);
      } catch (err) {
        console.error('Error checking bookmark:', err);
        ToastAndroid.show('Failed to check bookmark.', ToastAndroid.SHORT);
      } finally {
        setLoading(false);
      }
    };
    checkBookmark();
  }, [questionText]);

  // Toggle between add and remove
  const toggleBookmark = async () => {
    try {
      setLoading(true);

      if (isBookmarked) {
        // DELETE
        await axios.delete(`${BASE_URL}/${studentId}/${encodeURIComponent(questionText)}`);
        setIsBookmarked(false);
        ToastAndroid.show('Bookmark removed.', ToastAndroid.SHORT);
      } else {
        // POST
        await axios.post(BASE_URL, {
          studentId,
          questionText,
          options,
          correctOption,
        });
        setIsBookmarked(true);
        ToastAndroid.show('Bookmark added.', ToastAndroid.SHORT);
      }
    } catch (err) {
      console.error('Error toggling bookmark:', err);
      ToastAndroid.show('Failed to toggle bookmark.', ToastAndroid.SHORT);
    } finally {
      setLoading(false);
    }
  };

  if (loading) return <ActivityIndicator size="small" color="#000" />;

  return (
    <TouchableOpacity onPress={toggleBookmark}>
      <Icon
        name={isBookmarked ? 'bookmark' : 'bookmark-border'}
        size={24}
        color="#000"
      />
    </TouchableOpacity>
  );
};

export default BookmarkButton;
