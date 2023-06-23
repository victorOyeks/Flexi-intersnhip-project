package com.example.databaseschema.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException (String message) {
        super(message);
    }
}
