package com.is1di.remotecoffeemachine.controller;

import com.is1di.remotecoffeemachine.mapper.CoffeeMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeDto;
import com.is1di.remotecoffeemachine.service.CoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("coffee")
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    @Operation(
            summary = "Кофе по названию кофе"
    )
    @GetMapping("{name}")
    public CoffeeDto.Output getByName(@PathVariable String name) {
        return coffeeMapper.toOutput(coffeeService.getByName(name));
    }

    @Operation(
            summary = "Все кофе в кофе машине"
    )
    @GetMapping
    public List<CoffeeDto.Output> all() {
        return coffeeService.getAll().stream().map(coffeeMapper::toOutput).collect(Collectors.toList());
    }
}
