package com.is1di.remotecoffeemachine.model.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
public class OrderDomain {
    private UUID id;
    private LocalDateTime creationTimestamp;
    private LocalDateTime startTimestamp;
    private LocalDateTime closeTimestamp;
    private CoffeeStatusDomain status;
    private CoffeeDomain coffee;
}
