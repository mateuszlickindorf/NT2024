package com.mateusz.library.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BookNotAvailable {
    public static ResponseStatusException create(String title, String isbn) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("%1$s (ISBN: %2$s) not available!", title, isbn));
    }
}