package com.mateusz.library.library.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookInvalidNumber {
    public static ResponseStatusException create() {
        return new ResponseStatusException(HttpStatus.CONFLICT, "Number of available copies must have a non-negative value!");
    }
}