package com.example.databaseschema.controller;

import com.example.databaseschema.dto.ApiResponse;
import com.example.databaseschema.dto.AuthorDTO;
import com.example.databaseschema.model.Author;
import com.example.databaseschema.repository.AuthorRepository;
import com.example.databaseschema.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO savedAuthor = authorService.createAuthor(authorDTO);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Long authorId) {
        AuthorDTO updatedAuthor = authorService.editAuthorDetails(authorDTO, authorId);
        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Author> retrieveAuthor(@PathVariable Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        return foundAuthor.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity <List<AuthorDTO>> listAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(new ApiResponse("Author deleted successfully", true), HttpStatus.OK);
    }
}