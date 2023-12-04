package com.is1di.remotecoffeemachine.model.domain;

import com.is1di.remotecoffeemachine.config.Orders;
import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.CoffeeMachine;
import com.is1di.remotecoffeemachine.model.CoffeeMachineStatus;
import com.is1di.remotecoffeemachine.model.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;

@Data
@NoArgsConstructor
public class CoffeeMachineDefault implements CoffeeMachine {
    private Duration startUpTime;
    private Orders orders;
    private List<? extends Ingredient> ingredients;
    private volatile List<? extends CoffeeMachineStatus> statuses;
    private CoffeeMachineStatus currentStatus;
    private List<? extends Coffee> coffees;

    public CoffeeMachineDefault(Duration startUpTime, Orders orders, List<? extends Ingredient> ingredients, List<? extends CoffeeMachineStatus> statuses, List<? extends Coffee> coffees) {
        this.startUpTime = startUpTime;
        this.orders = orders;
        this.ingredients = ingredients;
        this.statuses = statuses;
        this.currentStatus = statuses.stream().filter(it -> it.getOrder() == 0).findFirst().orElseThrow(() -> new IllegalArgumentException("order with order = 0 not found"));
        this.coffees = coffees;
    }
}
