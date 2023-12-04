package com.is1di.remotecoffeemachine.controller.machine;

import com.is1di.remotecoffeemachine.mapper.CoffeeMachineMapper;
import com.is1di.remotecoffeemachine.model.dto.CoffeeMachineDto;
import com.is1di.remotecoffeemachine.service.CoffeeMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

/*    @GetMapping("stream")
    public SseEmitter streamStatus() {
        SseEmitter sse = new SseEmitter();
        ExecutorService sseExec = Executors.newSingleThreadExecutor();
        sseExec.execute(() -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(coffeeMachineService.)
            }
        });
    }*/
}
