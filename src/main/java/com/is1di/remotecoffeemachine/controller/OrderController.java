package com.is1di.remotecoffeemachine.controller;

import com.is1di.remotecoffeemachine.mapper.OrderMapper;
import com.is1di.remotecoffeemachine.model.dto.OrderDto;
import com.is1di.remotecoffeemachine.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Operation(
            summary = "Создание нового заказа",
            description = "Заказ ставится в очередь, если очередь пуста - заказ начинает готовиться"
    )
    @PostMapping
    public OrderDto.Output create(@RequestBody @Valid OrderDto.Create orderCreate) {
        return orderMapper.toOutput(orderService.create(orderMapper.toEntity(orderCreate)));
    }

    @Operation(
            summary = "Заказ по id"
    )
    @GetMapping("{id}")
    public OrderDto.Output getById(@PathVariable UUID id) {
        return orderMapper.toOutput(orderService.getById(id));
    }

    @Operation(
            summary = "Страница заказов"
    )
    @GetMapping
    public Page<OrderDto.Output> page(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return orderService.page(page, Math.max(limit, 10)).map(orderMapper::toOutput);
    }

    @Operation(
            summary = "Текущий заказ"
    )
    @GetMapping("current")
    public OrderDto.Output getCurrent() {
        return orderMapper.toOutput(orderService.currentOrder());
    }

    @Operation(
            summary = "SSE текущего заказа",
            description = "SSE (Server Sent Events) по текущему заказу"
    )
    @GetMapping("current/stream")
    public SseEmitter streamCurrentStatusOrder() {
        return orderService.streamById(orderService.currentOrder().getId());
    }

    @Operation(
            summary = "SSE заказа по id",
            description = "SSE (Server Sent Events) заказа по id"
    )
    @GetMapping("{id}/stream")
    public SseEmitter streamStatusOfOrder(@PathVariable UUID id) {
        return orderService.streamById(id);

    }

    @Operation(
            summary = "Отмена заказа"
    )
    @DeleteMapping("{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.delete(id);
    }
}
