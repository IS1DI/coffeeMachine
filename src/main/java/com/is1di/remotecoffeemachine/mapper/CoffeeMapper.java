package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.dto.CoffeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = UnitMapper.class)
public interface CoffeeMapper {
    CoffeeDto.Output toOutput(Coffee coffee);
}
