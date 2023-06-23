package com.example.databaseschema.dto;

import com.example.databaseschema.model.Author;
import com.example.databaseschema.model.Genre;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String title;
    private Integer publicationYear;
    private Double unitPrice;
    private Genre genre;
    private Author author;
}
