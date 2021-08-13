package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends IllegalArgumentException {
    private final Long id;
    private final String entity;

    public EntityNotFoundException(Long id, String entity) {
        super("The " + entity + " with Id " + id +  " not found.");
        this.id = id;
        this.entity = entity;
    }
}
