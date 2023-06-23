package com.example.databaseschema.controller;

import com.example.databaseschema.dto.ApiResponse;
import com.example.databaseschema.dto.GenreDTO;
import com.example.databaseschema.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres/")
public class GenreController {

    private final GenreService genreService;

    @PostMapping()
    public ResponseEntity<GenreDTO> createGenre (@RequestBody GenreDTO genreDTO) {
        GenreDTO createGenre = genreService.createGenre(genreDTO);
        return new ResponseEntity<>(createGenre, HttpStatus.CREATED);
    }

    @PutMapping("{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody GenreDTO genreDTO, @PathVariable Long genreId ) {
        GenreDTO updatedGenre = genreService.updateGenre(genreDTO, genreId);
        return new ResponseEntity<>(updatedGenre, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getGenre () {
        List<GenreDTO> genres = genreService.getAllGenres();
        return new ResponseEntity<>(genres, HttpStatus.OK);
    }

    @GetMapping("{genId}")
    public ResponseEntity <GenreDTO> getGenre(@PathVariable Long genId) {
        GenreDTO genreDTO = genreService.getGenre(genId);
        return new ResponseEntity<>(genreDTO, HttpStatus.OK);
    }

    @DeleteMapping("{genreId}")
    public ResponseEntity<ApiResponse> deleteGenre (@PathVariable Long genreId) {
        genreService.deleteGenre(genreId);
        return new ResponseEntity<>(new ApiResponse("Genre is deleted successfully", true), HttpStatus.OK);
    }
}
