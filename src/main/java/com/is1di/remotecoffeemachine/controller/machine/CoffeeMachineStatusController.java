package com.is1di.remotecoffeemachine.controller.machine;

import com.is1di.remotecoffeemachine.mapper.CoffeeMachineMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import com.is1di.remotecoffeemachine.service.CoffeeMachineService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("machine/status")
public class CoffeeMachineStatusController {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeMachineMapper coffeeMachineMapper;

    @Operation(
            summary = "Текущий статус кофе машины"
    )
    @GetMapping("current")
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output status() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.currentStatus());
    }

    @Operation(
            summary = "Метод включения кофе машины"
    )
    @PostMapping
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output turnOn() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.turnOn());
    }

    @Operation(
            summary = "Метод выключения кофе машины"
    )
    @DeleteMapping
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output turnOff() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.turnOff());
    }

    @Operation(
            summary = "Все статусы кофе машины"
    )
    @GetMapping
    public List<CoffeeMachineDto.CoffeeMachineStatusDto.Output> currentStatus() {
        return coffeeMachineService.statuses().stream()
                .map(coffeeMachineMapper::statusToOutput)
                .collect(Collectors.toList());
    }
}
