package com.is1di.remotecoffeemachine.model.dto;

import lombok.Data;

public final class UnitDto {
    @Data
    public static final class Output {
        private String unit;
        private String unitType;
        private String type;
    }
}
