package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.CoffeeMachine;
import com.is1di.remotecoffeemachine.model.CoffeeMachineStatus;
import com.is1di.remotecoffeemachine.model.Ingredient;
import com.is1di.remotecoffeemachine.model.domain.CoffeeMachineDefault;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;

@Service
@Transactional
@Order
@RequiredArgsConstructor
@Slf4j
public class DefaultCoffeeMachineService implements CoffeeMachineService {
    private final CoffeeMachineDefault coffeeMachine;

    @Override
    public synchronized CoffeeMachineStatus currentStatus() {
        return coffeeMachine.getCurrentStatus();
    }

    @Override
    public synchronized CoffeeMachineStatus turnOff() {
        coffeeMachine.setCurrentStatus(coffeeMachine.getStatuses().stream().min(Comparator.comparingInt(CoffeeMachineStatus::getOrder)).get());
        return coffeeMachine.getCurrentStatus();
    }

    @Override
    public synchronized CoffeeMachineStatus turnOn() {
        coffeeMachine.setCurrentStatus(coffeeMachine.getStatuses().stream().max(Comparator.comparingInt(CoffeeMachineStatus::getOrder)).get());
        return coffeeMachine.getCurrentStatus();
    }

    @Override
    public CoffeeMachine info() {
        return coffeeMachine;
    }

    @Override
    public long ordersLimit() {
        return coffeeMachine.getOrders().getMax();
    }

    @Override
    public boolean isEnabled() {
        return coffeeMachine.getCurrentStatus().getOrder() == 0;
    }

    @Override
    public Collection<? extends Ingredient> ingredients() {
        return coffeeMachine.getIngredients();
    }

    @Override
    public boolean readyForWork() {
        return coffeeMachine.getCurrentStatus().getOrder() == 0;
    }

    @Override
    public void makeCoffee(Coffee coffee) {
        log.info("starting making coffee");
        synchronized (coffeeMachine) {
            coffeeMachine.setCurrentStatus(coffeeMachine.getStatuses().stream().filter(it -> it.getOrder() == coffeeMachine.getCurrentStatus().getOrder() + 1).findFirst().get());
        }
        try {
            Thread.sleep(coffee.fullDurationOfCoffee());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("making coffee task - done, coffee - {}", coffee.getName());
        synchronized (coffeeMachine) {
            coffeeMachine.setCurrentStatus(coffeeMachine.getStatuses().stream().filter(it -> it.getOrder() == coffeeMachine.getCurrentStatus().getOrder() - 1).findFirst().get());
        }
    }

    @Override
    public Collection<? extends Coffee> allCoffee() {
        return coffeeMachine.getCoffees();
    }
}
