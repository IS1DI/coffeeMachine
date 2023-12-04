package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.exception.CoffeeMachineIsNotReadyException;
import com.is1di.remotecoffeemachine.message.MessageBase;
import com.is1di.remotecoffeemachine.model.domain.CoffeeStatusDomain;
import com.is1di.remotecoffeemachine.model.domain.OrderDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
public class OrderTask extends Thread {
    private final CoffeeMachineService coffeeMachineService;
    private final OrderDomain order;
    private final OrderService orderService;
    private final SseEmitter emitter;

    @Override
    public void run() {
        if (coffeeMachineService.isEnabled()) {
            log.debug("starting check for readiness");
            if (isReady()) {
                LocalDateTime startTime;
                log.debug("machine is ready and task started for order {}", order.getId());
                order.setStartTimestamp(startTime = LocalDateTime.now());
                Thread makingCoffeeThread = new Thread(() -> coffeeMachineService.makeCoffee(order.getCoffee()));
                makingCoffeeThread.start();
                for (CoffeeStatusDomain status : order.getCoffee().getStatuses()) {
                    try {
                        log.debug("status - {}, next starts after {}", status.getStatus(), status.getDuration());
                        order.setStatus(status);
                        orderService.update(order.getId(), order, (o1, o2) -> o2);
                        emitter.send(SseEmitter.event()
                                .data(order)
                                .id(status.getStatus())
                                .build());
                        Thread.sleep(status.getDuration().toMillis());
                    } catch (InterruptedException e) {
                        log.error("exception while sleeping in status {} order - {}", status.getStatus(), status.getOrder());
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (coffeeMachineService.readyForWork()) {
                    try {
                        order.setCloseTimestamp(LocalDateTime.now());
                        orderService.update(order.getId(), order, (o1, o2) -> o2);
                        log.debug("order {} closed successfully time spend = {}", order.getId(), Duration.between(startTime, LocalDateTime.now()));
                        emitter.send(SseEmitter.event()
                                .data("ORDER " + order.getId() + " successfully created")
                                .build());
                        this.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else {
                log.error("machine is not ready");
                throw new CoffeeMachineIsNotReadyException(
                        new MessageBase(MessageBase.MessageMethod.MACHINE_IS_NOT_READY)
                );
            }
        }
    }

    private boolean isReady() {
        for (int i = 0; i < 5 && !coffeeMachineService.readyForWork(); i++) {
            try {
                Thread.sleep(Duration.ofSeconds(5));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return coffeeMachineService.readyForWork();
    }
}
