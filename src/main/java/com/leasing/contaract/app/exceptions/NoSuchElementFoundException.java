package com.leasing.contaract.app.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSuchElementFoundException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(NoSuchElementFoundException.class);

    public NoSuchElementFoundException(String message) {
        super(message);
        logger.error("Error message: {}", message);
    }
}
