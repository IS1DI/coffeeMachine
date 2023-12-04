package com.is1di.remotecoffeemachine.model.domain;

import com.is1di.remotecoffeemachine.model.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CoffeeDomain implements Coffee {
    private String name;
    private List<IngredientDomain> ingredients;
    private List<CoffeeStatusDomain> statuses;
}
