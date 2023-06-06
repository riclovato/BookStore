package com.rick.bookStore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidJwtAuthenticationException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 8710497078417512752L;

    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
