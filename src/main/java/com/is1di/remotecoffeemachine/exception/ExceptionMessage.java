package com.is1di.remotecoffeemachine.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionMessage {
    private String message;
    private LocalDateTime timeStamp;

    public ExceptionMessage(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
