package com.watlas.projetoessentials.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class BadResquestException extends RuntimeException{

    public BadResquestException(String message) {
        super(message);
    }
}
