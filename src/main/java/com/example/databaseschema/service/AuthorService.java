package com.example.databaseschema.service;

import com.example.databaseschema.dto.AuthorDTO;
import com.example.databaseschema.model.Author;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();
    AuthorDTO createAuthor (AuthorDTO authorDTO);
    AuthorDTO editAuthorDetails(AuthorDTO authorDTO, Long authorId);
    void deleteAuthor (Long id);
}
