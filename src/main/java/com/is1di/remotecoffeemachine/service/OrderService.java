package com.is1di.remotecoffeemachine.service;

import com.is1di.remotecoffeemachine.exception.CoffeeMachineIsNotEnabledException;
import com.is1di.remotecoffeemachine.exception.LimitOutOfBoundsException;
import com.is1di.remotecoffeemachine.exception.NotFoundException;
import com.is1di.remotecoffeemachine.mapper.OrderDomainMapper;
import com.is1di.remotecoffeemachine.message.MessageBase;
import com.is1di.remotecoffeemachine.model.domain.OrderDomain;
import com.is1di.remotecoffeemachine.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.function.BiFunction;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final CoffeeMachineService coffeeMachineService;
    private final OrderRepository orderRepository;
    private final TaskScheduler taskScheduler;
    private final CoffeeService coffeeService;
    private final OrderDomainMapper orderDomainMapper;
    private final HashMap<UUID, Pair<SseEmitter,ScheduledFuture<?>>> schedulesMap = new HashMap<>();

    public void scheduleOrder(OrderDomain order) {
        SseEmitter emitter;
        schedulesMap.put(order.getId(), new Pair<>(emitter = new SseEmitter(currentDurationOfCoffee().toSeconds()), taskScheduler.schedule(createTask(order, emitter), ZonedDateTime.of(nextAvailableOrderTime(), ZoneId.systemDefault()).toInstant())));
    }

    public Runnable createTask(OrderDomain order, SseEmitter emitter) {
        return new OrderTask(coffeeMachineService, order, this, emitter);

    }

    public OrderDomain create(OrderDomain order) {
        long limit;
        if (coffeeMachineService.isEnabled())
            if ((limit = coffeeMachineService.ordersLimit()) > countOrders()) {
                scheduleOrder(order = orderDomainMapper.toDomain(orderRepository.save(orderDomainMapper.toEntity(order))));
                return order;
            } else
                throw new LimitOutOfBoundsException(new MessageBase(MessageBase.MessageMethod.ORDER_LIMIT_OUT_OF_BOUNDS, limit));
        else
            throw new CoffeeMachineIsNotEnabledException(new MessageBase(MessageBase.MessageMethod.MACHINE_IS_NOT_ENABLED));
    }

    public long countOrders() {
        return orderRepository.countByCloseTimestampNull();
    }

    public OrderDomain currentOrder() {
        return orderRepository.findFirstByStartTimestampNullOrderByCreationTimestampAsc().map(orderDomainMapper::toDomain).orElseThrow(() -> new NotFoundException(new MessageBase(MessageBase.MessageMethod.ORDER_NOT_FOUND))); //TODO
    }

    public Duration currentDurationOfCoffee() {
        return orderRepository.findByCloseTimestampNull().stream().reduce(
                Duration.ZERO,
                (prevDuration, order) -> {
                    Duration duration = coffeeService.getByName(order.getCoffee().getName()).fullDurationOfCoffee();
                    if (order.getStartTimestamp() != null) {
                        return prevDuration.plus(duration.minus(Duration.between(order.getStartTimestamp(), LocalDateTime.now())));
                    }
                    return prevDuration.plus(duration);
                }, Duration::plus
        );
    }

    public LocalDateTime nextAvailableOrderTime() {
        return LocalDateTime.now().plus(currentDurationOfCoffee());
    }

    public OrderDomain getById(UUID id) {
        return orderRepository.findById(id).map(orderDomainMapper::toDomain).orElseThrow(() -> new NotFoundException(
                        new MessageBase(MessageBase.MessageMethod.ORDER_NOT_FOUND)
                )
        );
    }

    public Page<OrderDomain> page(int page, int limit) {
        return orderRepository.findAll(PageRequest.of(Math.max(page, 1) - 1, Math.max(1, limit))).map(orderDomainMapper::toDomain);
    }

    public <DTO> OrderDomain update(UUID id, DTO order, BiFunction<OrderDomain, DTO, OrderDomain> mapper) {
        return orderDomainMapper.toDomain(orderRepository.save(orderDomainMapper.toEntity(mapper.apply(getById(id), order))));
    }

    public SseEmitter streamById(UUID id) {
        return schedulesMap.get(id).getValue1();
    }

    public boolean closeOrder(UUID id) {
        return schedulesMap.get(id).value2.cancel(false);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Pair<A,B> {
        private A value1;
        private B value2;
    }
}
