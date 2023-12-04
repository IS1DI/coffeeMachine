package com.is1di.remotecoffeemachine.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class StatusDto {
    @Data
    @Schema(name = "status.response")
    public static class Output {
        private String status;
    }
}
