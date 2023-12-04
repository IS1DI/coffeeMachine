package com.is1di.remotecoffeemachine.model.entity;

import com.is1di.remotecoffeemachine.model.CoffeeStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.Duration;

@Data
@Embeddable
public class CoffeeStatusEntity implements CoffeeStatus {
    private Duration duration;
    private String status;
    @Column(name = "coffee_status_order")
    private Integer order;
}
