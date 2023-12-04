package com.is1di.remotecoffeemachine.config;

import com.is1di.remotecoffeemachine.model.Ingredient;
import com.is1di.remotecoffeemachine.model.UnitConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientExternal implements Ingredient {
    private UnitConverter.Unit unit;
    private Double amount;
    private String ingredient;
}
