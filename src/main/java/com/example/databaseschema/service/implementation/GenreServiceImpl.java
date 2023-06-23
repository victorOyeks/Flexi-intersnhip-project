package com.example.databaseschema.service.implementation;

import com.example.databaseschema.dto.GenreDTO;
import com.example.databaseschema.exceptions.GenreNotFoundException;
import com.example.databaseschema.model.Genre;
import com.example.databaseschema.repository.GenreRepository;
import com.example.databaseschema.service.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    private ModelMapper modelMapper;

    @Autowired
    public void setGenreRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public GenreDTO createGenre(@Valid GenreDTO genreDTO) {
        Genre genre = genreDtoToGenre(genreDTO);
        Genre savedGenre = genreRepository.save(genre);
        return genreToGenreDto(savedGenre);
    }

    @Override
    public GenreDTO updateGenre(@Valid GenreDTO genreDTO, Long id) {
        Genre genre = genreRepository.findByGenreId(id);
        if (genre == null) {
            throw new GenreNotFoundException("Genre not found with ID: " + id);
        }
        genre.setName(genreDTO.getName());
        Genre updatedGenre = genreRepository.save(genre);
        return genreToGenreDto(updatedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(this::genreToGenreDto).collect(Collectors.toList());
    }

    @Override
    public GenreDTO getGenre(Long genreId) {
        Genre genre = genreRepository.findByGenreId(genreId);
        if (genre == null) {
            throw new GenreNotFoundException("Genre not found with ID: " + genreId);
        }
        return genreToGenreDto(genre);
    }

    @Override
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    private Genre genreDtoToGenre(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    private GenreDTO genreToGenreDto(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }
}

