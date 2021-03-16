package com.watlas.projetoessentials.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidadorExceptionDetails extends ExceptionDetails{
    private final String fields;
    private final String filedsMessage;
}
