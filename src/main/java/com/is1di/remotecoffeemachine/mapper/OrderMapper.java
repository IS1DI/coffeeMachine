package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.domain.OrderDomain;
import com.is1di.remotecoffeemachine.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "coffee.name", source = "coffeeName")
    OrderDomain toEntity(OrderDto.Create createDto);

    @Mapping(target = "startedAt", source = "startTimestamp")
    @Mapping(target = "createdAt", source = "creationTimestamp")
    @Mapping(target = "coffeeStatus", source = "status")
    @Mapping(target = "coffee", source = "coffee.name")
    OrderDto.Output toOutput(OrderDomain orderDomain);
}
