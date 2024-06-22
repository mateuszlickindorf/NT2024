package com.mateusz.library.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReturnPending {
    public static ResponseStatusException create(String title) {
        return new ResponseStatusException(HttpStatus.CONFLICT, String.format("The book '%1$s' hasn't been returned yet!", title));
    }
}
