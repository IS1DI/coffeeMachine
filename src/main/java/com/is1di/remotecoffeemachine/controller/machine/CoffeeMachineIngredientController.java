package com.is1di.remotecoffeemachine.controller.machine;

import com.is1di.remotecoffeemachine.mapper.CoffeeMachineMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import com.is1di.remotecoffeemachine.service.CoffeeMachineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("machine/ingredient")
public class CoffeeMachineIngredientController {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeMachineMapper coffeeMachineMapper;

    @Operation(
            summary = "Все ингредиенты доступные в кофе машине"
    )
    @GetMapping
    public List<CoffeeMachineDto.IngredientDto.Output> all() {
        return coffeeMachineService.ingredients().stream()
                .map(coffeeMachineMapper::toIngredientOutput)
                .collect(Collectors.toList());
    }
}
