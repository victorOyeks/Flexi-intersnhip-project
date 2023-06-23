package com.example.databaseschema.repository;

import com.example.databaseschema.dto.BookDTO;
import com.example.databaseschema.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {
    Book findByBookId (Long id);
}
