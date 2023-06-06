package com.rick.bookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8710497078417512752L;

    public ResourceNotFoundException(String ex) {
        super(ex);
    }
}
