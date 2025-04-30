package com.example.prepbuddy.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkRepo bookmarkRepo;

    @Autowired
    public BookmarkController(BookmarkRepo bookmarkRepo) {
        this.bookmarkRepo = bookmarkRepo;
    }

    // ✅ Get all bookmarks for a specific student
    @GetMapping("/{studentId}")
    public List<Bookmark> getBookmarksForStudent(@PathVariable String studentId) {
        return bookmarkRepo.getBookmarksForStudent(studentId);
    }

    // ✅ Delete a bookmark by student ID and question text
    @DeleteMapping("/{studentId}/{questionText}")
    public ResponseEntity<String> deleteBookmark(
            @PathVariable String studentId,
            @PathVariable String questionText
    ) {
        boolean deleted = bookmarkRepo.deleteBookmarkByStudentAndQuestion(studentId, questionText);
        if (deleted) {
            return ResponseEntity.ok("Bookmark deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Bookmark not found.");
        }
    }
}
