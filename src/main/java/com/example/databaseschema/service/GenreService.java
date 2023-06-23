package com.example.databaseschema.service;

import com.example.databaseschema.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO createGenre(GenreDTO genreDTO);
    GenreDTO updateGenre (GenreDTO genreDTO, Long id);
    void deleteGenre (Long genreId);
    GenreDTO getGenre(Long genreId);
    List<GenreDTO> getAllGenres();
}
