package com.mateusz.library.library.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotFound {
    public static ResponseStatusException create(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Book with id '%s' not found!", id));
    }
}