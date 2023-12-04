package com.is1di.remotecoffeemachine.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

public final class OrderDto {

    @Data
    @Schema(name = "order.request")
    public static final class Create {
        @NotBlank(message = "{order.coffee.error.validation.empty}")
        private String coffeeName;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Schema(name = "order.response")
    public static final class Output {
        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime startedAt;
        private CoffeeDto.CoffeeStatusDto.Output coffeeStatus;
        private String coffee;
    }
}
