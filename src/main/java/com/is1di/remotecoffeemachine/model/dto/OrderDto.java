package com.is1di.remotecoffeemachine.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

public final class OrderDto {

    @Data
    public static final class Create {
        private String coffeeName;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static final class Output {
        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime startedAt;
        private CoffeeDto.CoffeeStatusDto.Output coffeeStatus;
        private String coffee;
    }
}
