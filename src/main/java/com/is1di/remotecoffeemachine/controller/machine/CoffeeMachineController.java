package com.is1di.remotecoffeemachine.controller.machine;

import com.is1di.remotecoffeemachine.mapper.CoffeeMachineMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import com.is1di.remotecoffeemachine.service.CoffeeMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("machine")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;
    private final CoffeeMachineMapper coffeeMachineMapper;

    @GetMapping
    public CoffeeMachineDto.Output info() {
        return coffeeMachineMapper.toOutput(coffeeMachineService.info());
    }
}
