package com.is1di.remotecoffeemachine.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.is1di.remotecoffeemachine.model.domain.*;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class CoffeeMachineDefaultConfig {
    private static final String dateFormat = "yyyy-MM-dd";
    private static final String dateTimeFormat = "dd-MM-yyyy HH:mm:ss";

    @Bean
    @Scope("singleton")
    public CoffeeMachineDefault getCoffeeMachine() {
        return new CoffeeMachineDefault(Duration.ofSeconds(10),
                new Orders(10L),
                List.of(new IngredientExternal(UnitConverter.Unit.LITRE, 10d, "milk"),
                        new IngredientExternal(UnitConverter.Unit.KILOGRAM, 2d, "coffee"),
                        new IngredientExternal(UnitConverter.Unit.LITRE, 300d, "water")),
                List.of(new CoffeeMachineStatusDefault("ON", 0),
                        new CoffeeMachineStatusDefault("WORKING", 1),
                        new CoffeeMachineStatusDefault("OFF", 2)),
                List.of(new CoffeeDefault(
                        "Капучино",
                        List.of(new IngredientDefault(UnitConverter.Unit.MILLILITRE, 100d, "milk"),
                                new IngredientDefault(UnitConverter.Unit.GRAM, 300d, "coffee"),
                                new IngredientDefault(UnitConverter.Unit.MILLILITRE, 300d, "water")),
                        List.of(new CoffeeStatusDefault("start", 0, Duration.ofSeconds(15)),
                                new CoffeeStatusDefault("adding coffee", 1, Duration.ofSeconds(20)),
                                new CoffeeStatusDefault("adding milk", 2, Duration.ofSeconds(30)),
                                new CoffeeStatusDefault("adding water", 3, Duration.ofSeconds(50)))))
        );
    }

    @Bean
    @Scope("singleton")
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(Executors.newSingleThreadScheduledExecutor());
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.simpleDateFormat(dateTimeFormat);
            builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
            builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
        };
    }
}
