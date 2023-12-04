package com.is1di.remotecoffeemachine.model.dto;

import lombok.Data;

public class StatusDto {
    @Data
    public static class Output {
        private String status;
    }
}
