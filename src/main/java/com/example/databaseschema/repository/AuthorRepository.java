package com.example.databaseschema.repository;

import com.example.databaseschema.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {
    Author findByAuthorId (Long id);
}
