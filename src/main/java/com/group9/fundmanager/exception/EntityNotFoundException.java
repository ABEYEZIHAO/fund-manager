package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends IllegalArgumentException {

    public EntityNotFoundException(Long id, String entity) {
        super("The " + entity + " with Id " + id +  " not found.");
    }
}
