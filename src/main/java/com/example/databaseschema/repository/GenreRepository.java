package com.example.databaseschema.repository;

import com.example.databaseschema.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Long> {
    Genre findByGenreId (Long id);
}
