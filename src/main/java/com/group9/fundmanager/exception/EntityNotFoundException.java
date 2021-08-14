package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends IllegalArgumentException {
    private final Long Id;

    public EntityNotFoundException(Long Id) {
        super("Fund with Id " + Id +  " not found.");
        this.Id = Id;
    }
}
