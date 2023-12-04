package com.is1di.remotecoffeemachine.exception;

import com.is1di.remotecoffeemachine.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final MessageService messageService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionMessage notFound(NotFoundException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CoffeeMachineIsNotReadyException.class)
    public ExceptionMessage notFound(CoffeeMachineIsNotReadyException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CoffeeMachineIsNotEnabledException.class)
    public ExceptionMessage notFound(CoffeeMachineIsNotEnabledException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LimitOutOfBoundsException.class)
    public ExceptionMessage notFound(LimitOutOfBoundsException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OutOfIngredientException.class)
    public ExceptionMessage notFound(OutOfIngredientException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }
}
