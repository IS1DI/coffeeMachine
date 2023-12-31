package com.is1di.remotecoffeemachine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.util.List;

public final class CoffeeDto {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "coffee.response")
    public static final class Output {
        private String name;
        private List<CoffeeIngredient.Output> ingredients;
        private List<CoffeeStatusDto.Output> statuses;
    }

    public static final class CoffeeStatusDto {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Schema(name = "coffee.status.response")
        public static final class Output extends StatusDto.Output {
            private Duration duration;
        }
    }

    public static final class CoffeeIngredient {
        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(name = "coffee.ingredient.response")
        public static final class Output {
            private String name;
            private Double amount;
            private UnitDto.Output unit;
        }
    }
}
