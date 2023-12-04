package com.is1di.remotecoffeemachine.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class OrderDomain {
    private UUID id;
    private LocalDateTime creationTimestamp;
    private LocalDateTime startTimestamp;
    private LocalDateTime closeTimestamp;
    private CoffeeStatusDefault status;
    private CoffeeDefault coffee;
}
