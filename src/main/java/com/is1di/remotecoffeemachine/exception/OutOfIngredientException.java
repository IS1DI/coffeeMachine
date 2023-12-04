package com.is1di.remotecoffeemachine.exception;

import com.is1di.remotecoffeemachine.message.MessageBase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OutOfIngredientException extends RuntimeException {
    private final MessageBase messageBase;
}
