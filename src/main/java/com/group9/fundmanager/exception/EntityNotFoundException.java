package com.group9.fundmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author abe
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends IllegalArgumentException {
    private final Long fundId;

    public EntityNotFoundException(Long fundId) {
        super("User with Id " + fundId +  " not found.");
        this.fundId = fundId;
    }
}
