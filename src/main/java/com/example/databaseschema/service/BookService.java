package com.example.databaseschema.service;

import com.example.databaseschema.dto.BookDTO;
import com.example.databaseschema.model.Book;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();
    BookDTO getBook(Long bookId);
    BookDTO updateBook (BookDTO updatedBook, Long bookId);
    public BookDTO createBook(BookDTO bookDTO, Long authorId, Long genreId);//    Book updateBook(Long id, Book updatedBook);
    void deleteBook(Long id);
    boolean isBookExist(BookDTO bookDTO);
}
