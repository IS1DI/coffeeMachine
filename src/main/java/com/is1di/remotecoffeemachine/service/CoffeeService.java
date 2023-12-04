package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.exception.NotFoundException;
import com.is1di.remotecoffeemachine.mapper.CoffeeDomainMapper;
import com.is1di.remotecoffeemachine.message.MessageBase;
import com.is1di.remotecoffeemachine.model.domain.CoffeeDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeDomainMapper coffeeDomainMapper;

    public CoffeeDomain getByName(String name) {
        return coffeeMachineService.allCoffee().stream()
                .filter(coffee -> coffee.getName().equals(name))
                .findFirst()
                .map(coffeeDomainMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(
                        new MessageBase(MessageBase.MessageMethod.COFFEE_NOT_FOUND, name)
                ));
    }

    public List<CoffeeDomain> getAll() {
        return coffeeMachineService.allCoffee().stream().map(coffeeDomainMapper::toDomain).collect(Collectors.toList());
    }
}
