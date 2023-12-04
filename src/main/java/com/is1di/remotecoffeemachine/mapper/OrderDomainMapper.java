package com.is1di.remotecoffeemachine.mapper;

import com.is1di.remotecoffeemachine.model.domain.CoffeeDefault;
import com.is1di.remotecoffeemachine.model.domain.OrderDomain;
import com.is1di.remotecoffeemachine.model.entity.OrderEntity;
import com.is1di.remotecoffeemachine.service.CoffeeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class OrderDomainMapper {
    private CoffeeService coffeeService;

    @Mapping(target = "coffee", qualifiedByName = "getCoffee", source = "coffee.name")
    public abstract OrderDomain toDomain(OrderEntity entity);

    public abstract OrderEntity toEntity(OrderDomain order);

    @Named("getCoffee")
    public CoffeeDefault getCoffee(String name) {
        return coffeeService.getByName(name);
    }

    @Autowired
    void setCoffeeService(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }
}
