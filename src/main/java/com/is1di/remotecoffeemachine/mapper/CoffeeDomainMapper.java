package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.domain.CoffeeDefault;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeDomainMapper {
    CoffeeDefault toDomain(Coffee abstractCoffee);
}
