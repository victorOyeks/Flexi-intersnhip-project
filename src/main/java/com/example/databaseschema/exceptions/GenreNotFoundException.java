package com.example.databaseschema.exceptions;

public class GenreNotFoundException extends RuntimeException{

    public GenreNotFoundException (String message){
        super(message);
    }
}
