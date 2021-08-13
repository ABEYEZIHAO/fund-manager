package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NameAlreadyInUseException extends IllegalArgumentException {

    public NameAlreadyInUseException(String entity, String name) {
        super(entity + ' ' + name + " already exists.");
    }
}
