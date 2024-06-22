package com.mateusz.library.library.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundForId {
    public static ResponseStatusException create(Long id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id '%s' was not found!", id));
    }
}
