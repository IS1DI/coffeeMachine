package com.is1di.remotecoffeemachine.model;

import java.util.List;

public interface CoffeeMachine {
    List<? extends CoffeeMachineStatus> getStatuses();

    List<? extends Ingredient> getIngredients();

    <T extends CoffeeMachineStatus> T getCurrentStatus();

    List<? extends Coffee> getCoffees();
}
