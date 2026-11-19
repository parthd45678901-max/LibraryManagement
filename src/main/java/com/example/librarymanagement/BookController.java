package com.example.librarymanagement;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    List<Book> books = new ArrayList<>();

    // A. Add New Book
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {

        books.add(book);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Book added successfully!");
    }

    // B. Get All Books
    @GetMapping
    public List<Book> getAllBooks() {

        return books;
    }

    // C. Get Book By ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {

        for (Book b : books) {

            if (b.getId() == id) {
                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // D. Update Book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable int id,
            @RequestBody Book newBook) {

        for (Book b : books) {

            if (b.getId() == id) {

                b.setTitle(newBook.getTitle());
                b.setAuthor(newBook.getAuthor());
                b.setCategory(newBook.getCategory());
                b.setAvailableCopies(newBook.getAvailableCopies());

                return ResponseEntity.ok("Book updated successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found!");
    }

    // E. Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id) {

        for (Book b : books) {

            if (b.getId() == id) {

                books.remove(b);

                return ResponseEntity.ok("Book deleted successfully!");
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Book not found!");
    }
}