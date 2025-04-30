
const API_BASE_URL = 'http://localhost:8080/'; 

const BookmarkRepo = {
  getBookmarkedQuestionsForCurrentUser: async () => {
    const response = await fetch(`${API_BASE_URL}/currentUser`);
    if (!response.ok) {
      throw new Error('Failed to fetch bookmarked questions');
    }
    return await response.json();
  },

  removeBookmark: async (questionId) => {
    const response = await fetch(`${API_BASE_URL}/${questionId}`, {
      method: 'DELETE',
    });
    if (!response.ok) {
      throw new Error('Failed to remove bookmark');
    }
  },
};

export default BookmarkRepo;
