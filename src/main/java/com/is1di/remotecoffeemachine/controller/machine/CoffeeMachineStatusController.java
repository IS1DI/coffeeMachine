package com.is1di.remotecoffeemachine.controller.machine;

import com.is1di.remotecoffeemachine.mapper.CoffeeMachineMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import com.is1di.remotecoffeemachine.service.CoffeeMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("machine/status")
public class CoffeeMachineStatusController {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeMachineMapper coffeeMachineMapper;

    @GetMapping("current")
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output status() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.currentStatus());
    }

    @PostMapping
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output turnOn() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.turnOn());
    }

    @DeleteMapping
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output turnOff() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.turnOff());
    }

    @GetMapping
    public CoffeeMachineDto.CoffeeMachineStatusDto.Output currentStatus() {
        return coffeeMachineMapper.statusToOutput(coffeeMachineService.currentStatus());
    }
}
