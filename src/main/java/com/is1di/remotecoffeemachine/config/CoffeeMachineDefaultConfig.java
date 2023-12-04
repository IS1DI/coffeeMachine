package com.is1di.remotecoffeemachine.config;

import com.is1di.remotecoffeemachine.model.UnitConverter;
import com.is1di.remotecoffeemachine.model.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import java.time.Duration;
import java.util.List;

@Configuration
public class CoffeeMachineDefaultConfig {
    @Bean
    @Scope("singleton")
    @Order
    public CoffeeMachineDefault getCoffeeMachine() {
        return new CoffeeMachineDefault(Duration.ofSeconds(10),
                new Orders(10L),
                List.of(new IngredientExternal(UnitConverter.Unit.LITRE, 10d, "milk"),
                        new IngredientExternal(UnitConverter.Unit.KILOGRAM, 2d, "coffee"),
                        new IngredientExternal(UnitConverter.Unit.LITRE, 300d, "water")),
                List.of(new CoffeeMachineStatusDefault("ON", 0),
                        new CoffeeMachineStatusDefault("WORKING", 1),
                        new CoffeeMachineStatusDefault("OFF", 2)),
                List.of(new CoffeeDomain(
                        "Капучино",
                        List.of(new IngredientDomain(UnitConverter.Unit.MILLILITRE, 100d, "milk"),
                                new IngredientDomain(UnitConverter.Unit.GRAM, 300d, "coffee"),
                                new IngredientDomain(UnitConverter.Unit.MILLILITRE, 300d, "water")),
                        List.of(new CoffeeStatusDomain("start", 0, Duration.ofSeconds(15)),
                                new CoffeeStatusDomain("adding coffee", 1, Duration.ofSeconds(20)),
                                new CoffeeStatusDomain("adding milk", 2, Duration.ofSeconds(30)),
                                new CoffeeStatusDomain("adding water", 3, Duration.ofSeconds(50)))))
        );
    }
}
