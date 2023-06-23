package com.example.databaseschema.service.implementation;

import com.example.databaseschema.dto.BookDTO;
import com.example.databaseschema.exceptions.AuthorNotFoundException;
import com.example.databaseschema.exceptions.BookNotFoundException;
import com.example.databaseschema.exceptions.GenreNotFoundException;
import com.example.databaseschema.model.Author;
import com.example.databaseschema.model.Book;
import com.example.databaseschema.model.Genre;
import com.example.databaseschema.repository.AuthorRepository;
import com.example.databaseschema.repository.GenreRepository;
import com.example.databaseschema.service.BookService;
import com.example.databaseschema.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.modelMapper = modelMapper;
    }

    public BookDTO createBook(@Valid BookDTO bookDTO, @NotNull Long authorId, @NotNull Long genreId) {
        Author author = authorRepository.findByAuthorId(authorId);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + authorId);
        }

        Genre genre = genreRepository.findByGenreId(genreId);
        if (genre == null) {
            throw new GenreNotFoundException("Genre not found with ID: " + genreId);
        }

        Book book = bookDtoToBook(bookDTO);
        book.setAuthor(author);
        book.setGenre(genre);
        Book savedBook = bookRepository.save(book);
        return bookToBookDTO(savedBook);
    }

    public BookDTO updateBook(@Valid BookDTO bookDTO, @NotNull Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

        book.setTitle(bookDTO.getTitle());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setUnitPrice(bookDTO.getUnitPrice());
        Book updatedBook = bookRepository.save(book);
        return bookToBookDTO(updatedBook);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(this::bookToBookDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBook(@NotNull Long bookId) {
        Book book = bookRepository.findByBookId(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        return bookToBookDTO(book);
    }

    public void deleteBook(@NotNull Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean isBookExist(@NotNull BookDTO bookDTO) {
        return bookRepository.existsById(bookDTO.getBookId());
    }

    private Book bookDtoToBook(BookDTO bookDTO) {
        return modelMapper.map(bookDTO, Book.class);
    }

    private BookDTO bookToBookDTO(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }
}
