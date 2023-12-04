package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.CoffeeMachine;
import com.is1di.remotecoffeemachine.model.CoffeeMachineStatus;
import com.is1di.remotecoffeemachine.model.Ingredient;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CoffeeMachineMapper {
    public abstract CoffeeMachineDto.CoffeeMachineStatusDto.Output statusToOutput(CoffeeMachineStatus status);

    public abstract CoffeeMachineDto.Output toOutput(CoffeeMachine coffeeMachine);

    @Mapping(target = "currentAmount", source = "amount")
    public abstract CoffeeMachineDto.IngredientDto.Output toIngredientOutput(Ingredient ingredient);
}
