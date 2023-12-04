package com.is1di.remotecoffeemachine.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public final class UnitDto {
    @Data
    @Schema(name = "unit.response")
    public static final class Output {
        private String unit;
        private String unitType;
        private String type;
    }
}
