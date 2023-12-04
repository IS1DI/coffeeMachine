package com.is1di.remotecoffeemachine.model.entity;

import com.is1di.remotecoffeemachine.model.UnitConverter;
import com.is1di.remotecoffeemachine.model.Ingredient;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class IngredientEntity implements Ingredient {
    private UnitConverter.Unit unit;
    private Double amount;
    private String ingredient;
}
