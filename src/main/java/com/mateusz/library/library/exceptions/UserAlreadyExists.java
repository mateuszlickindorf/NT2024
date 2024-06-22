package com.mateusz.library.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExists extends RuntimeException{
    private UserAlreadyExists(String message) {
        super(message);
    }

    public static ResponseStatusException create(String username) {
        UserAlreadyExists exception = new UserAlreadyExists(String.format("Username: %s already exists!", username));
        return new ResponseStatusException(HttpStatus.CONFLICT, exception.getMessage(), exception );
    }
}
