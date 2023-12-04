package com.is1di.remotecoffeemachine.model.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CoffeeEntity {
    private String name;
}
