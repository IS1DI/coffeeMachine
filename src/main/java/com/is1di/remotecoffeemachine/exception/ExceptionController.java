package com.is1di.remotecoffeemachine.exception;

import com.is1di.remotecoffeemachine.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionController {
    private final MessageService messageService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionMessage notFound(NotFoundException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.TOO_EARLY)
    @ExceptionHandler(CoffeeMachineIsNotReadyException.class)
    public ExceptionMessage notFound(CoffeeMachineIsNotReadyException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(CoffeeMachineIsNotEnabledException.class)
    public ExceptionMessage notFound(CoffeeMachineIsNotEnabledException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(LimitOutOfBoundsException.class)
    public ExceptionMessage notFound(LimitOutOfBoundsException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OutOfIngredientException.class)
    public ExceptionMessage notFound(OutOfIngredientException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.TOO_EARLY)
    @ExceptionHandler(OrderNotStartedException.class)
    public ExceptionMessage notFound(OrderNotStartedException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(OrderException.class)
    public ExceptionMessage notFound(OrderException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(OrderCannotCloseException.class)
    public ExceptionMessage notFound(OrderCannotCloseException ex) {
        return new ExceptionMessage(messageService.getMessage(ex.getMessageBase()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> validate(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
