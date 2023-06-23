package com.example.databaseschema.service.implementation;

import com.example.databaseschema.dto.AuthorDTO;
import com.example.databaseschema.dto.BookDTO;
import com.example.databaseschema.exceptions.AuthorNotFoundException;
import com.example.databaseschema.exceptions.InvalidAuthorException;
import com.example.databaseschema.model.Author;
import com.example.databaseschema.model.Book;
import com.example.databaseschema.repository.AuthorRepository;
import com.example.databaseschema.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        validateAuthorDTO(authorDTO);
        Author author = authorDtoToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorToAuthorDTO(savedAuthor);
    }

    @Override
    public AuthorDTO editAuthorDetails(AuthorDTO authorDTO, Long authorId) {
        validateAuthorDTO(authorDTO);
        Author author = authorRepository.findByAuthorId(authorId);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + authorId);
        }
        author.setName(authorDTO.getName());
        author.setDateOfBirth(authorDTO.getDateOfBirth());
        Author updatedAuthor = authorRepository.save(author);
        return authorToAuthorDTO(updatedAuthor);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(this::authorToAuthorDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Author author = authorRepository.findByAuthorId(authorId);
        if (author == null) {
            throw new AuthorNotFoundException("Author not found with ID: " + authorId);
        }
        authorRepository.deleteById(authorId);
    }

    private Author authorDtoToAuthor(AuthorDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }

    private AuthorDTO authorToAuthorDTO(Author author) {
        return modelMapper.map(author, AuthorDTO.class);
    }

    private void validateAuthorDTO(AuthorDTO authorDTO) {
        if (authorDTO.getName() == null || authorDTO.getName().isEmpty()) {
            throw new InvalidAuthorException("Author name is required");
        }
    }
}
