package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.CoffeeMachine;
import com.is1di.remotecoffeemachine.model.CoffeeMachineStatus;
import com.is1di.remotecoffeemachine.model.Ingredient;

import java.util.Collection;

public interface CoffeeMachineService {
    /**
     * @return current status of coffeeMachine
     */
    CoffeeMachineStatus currentStatus();

    /**
     * turn off coffeeMachine
     *
     * @return coffeeMachine turned-off status
     */
    CoffeeMachineStatus turnOff();

    /**
     * turn on coffeeMachine
     *
     * @return coffeeMachine info with turned-on status
     */
    CoffeeMachineStatus turnOn();

    /**
     * coffeeMachine info
     *
     * @return coffeeMachine info
     */
    CoffeeMachine info();

    /**
     * limit of orders for coffeeMachine
     *
     * @return max of orders that can be in order queue
     */
    long ordersLimit();

    /**
     * @return true - coffeeMachine is turnedOn, false otherwise
     */
    boolean isEnabled();

    /**
     * @return ingredient list that coffeeMachine can work with
     */
    Collection<? extends Ingredient> ingredients();

    /**
     * @return true - coffeeMachine can make new coffee, else - otherwise
     */
    boolean readyForWork();

    /**
     * @param coffee - coffee with ingredients
     */
    void makeCoffee(Coffee coffee);

    /**
     * @return list of all coffee's
     */
    Collection<? extends Coffee> allCoffee();
}
