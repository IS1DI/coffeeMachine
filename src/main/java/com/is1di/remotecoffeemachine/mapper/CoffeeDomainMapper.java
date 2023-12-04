package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.Coffee;
import com.is1di.remotecoffeemachine.model.domain.CoffeeDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeDomainMapper {
    CoffeeDomain toDomain(Coffee abstractCoffee);
}
