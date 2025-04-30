package com.example.prepbuddy.backend;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookmarkRepo {

    private final Firestore db;

    public BookmarkRepo(Firestore firestore) {
        this.db = firestore;
    }

    public List<Bookmark> getBookmarksForStudent(String studentId) {
        List<Bookmark> bookmarkList = new ArrayList<>();
        try {
            CollectionReference bookmarks = db.collection("bookmarks");

            Query query = bookmarks.whereEqualTo("StudentID", studentId);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Bookmark bookmark = document.toObject(Bookmark.class);
                bookmarkList.add(bookmark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookmarkList;
    }

    public boolean deleteBookmarkByStudentAndQuestion(String studentId, String questionText) {
        try {
            System.out.println("Deleting bookmark for student: " + studentId + " and question: " + questionText);
    
            CollectionReference bookmarks = db.collection("bookmarks");
            Query query = bookmarks
                .whereEqualTo("StudentID", studentId)
                .whereEqualTo("QuestionText", questionText);
    
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
    
            System.out.println("Found " + documents.size() + " matching documents.");
    
            if (!documents.isEmpty()) {
                DocumentReference documentRef = documents.get(0).getReference();
                ApiFuture<WriteResult> delete = documentRef.delete();
                return delete.get().getUpdateTime() != null;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
