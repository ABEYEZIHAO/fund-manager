package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NameAlreadyInUseException extends IllegalArgumentException {
    private final String name;

    public NameAlreadyInUseException(String name) {
        super("User with email " + name + " already exists.");
        this.name = name;
    }
}
