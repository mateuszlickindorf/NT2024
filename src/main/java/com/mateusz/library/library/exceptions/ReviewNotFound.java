package com.mateusz.library.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReviewNotFound {
    public static ResponseStatusException create(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Review with id '%s' not found!", id));
    }
}
