package com.example.databaseschema.controller;

import com.example.databaseschema.dto.ApiResponse;
import com.example.databaseschema.dto.BookDTO;
import com.example.databaseschema.model.Book;
import com.example.databaseschema.repository.BookRepository;
import com.example.databaseschema.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/books/")
public class BookController {

    private final BookService bookService;

    @PostMapping("author/{authorId}/genre/{genreId}/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO, @PathVariable Long authorId, @PathVariable Long genreId) {
        BookDTO savedBook = bookService.createBook(bookDTO, authorId, genreId);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping("{bookId}")
    public ResponseEntity<BookDTO> updateBook (@RequestBody BookDTO bookDTO, @PathVariable Long bookId) {
        BookDTO updatedBook = bookService.updateBook(bookDTO, bookId);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> retrieveBook(@PathVariable Long id) {
        BookDTO foundBook = bookService.getBook(id);
        return new ResponseEntity<>(foundBook, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity <List<BookDTO>> listBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(new ApiResponse("Book deleted successfully", true), HttpStatus.OK);
    }
}