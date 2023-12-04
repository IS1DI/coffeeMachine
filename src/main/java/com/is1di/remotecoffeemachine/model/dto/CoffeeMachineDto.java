package com.is1di.remotecoffeemachine.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.util.List;

public class CoffeeMachineDto {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "coffeeMachine.response")
    public static final class Output {
        private Duration startUpTime;
        private Integer maxOrders;
        private List<IngredientDto.Output> ingredients;
        private CoffeeMachineStatusDto.Output status;
    }

    public static final class CoffeeMachineStatusDto {
        @EqualsAndHashCode(callSuper = true)
        @Data
        @Schema(name = "coffeeMachine.status.response")
        public static final class Output extends StatusDto.Output {
        }
    }

    public static final class IngredientDto {
        @Data
        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Schema(name = "coffeeMachine.ingredient.response")
        public static final class Output {
            private String ingredient;
            private String currentAmount;
            private String countAfterOrdersComplete;
        }
    }
}
