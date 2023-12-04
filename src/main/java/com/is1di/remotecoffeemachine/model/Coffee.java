package com.is1di.remotecoffeemachine.model;

import java.time.Duration;
import java.util.Collection;

public interface Coffee {
    String getName();

    Collection<? extends Ingredient> getIngredients();

    Collection<? extends CoffeeStatus> getStatuses();

    default Duration fullDurationOfCoffee() {
        return getStatuses().stream().reduce(Duration.ZERO,
                (prevDuration, status) -> prevDuration.plus(status.getDuration()),
                Duration::plus);
    }
}
