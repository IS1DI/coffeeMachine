package com.is1di.remotecoffeemachine.controller;

import com.is1di.remotecoffeemachine.mapper.OrderMapper;
import com.is1di.remotecoffeemachine.model.dto.OrderDto;
import com.is1di.remotecoffeemachine.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public OrderDto.Output create(@RequestBody OrderDto.Create orderCreate) {
        return orderMapper.toOutput(orderService.create(orderMapper.toEntity(orderCreate)));
    }

    @GetMapping("{id}")
    public OrderDto.Output getById(@PathVariable UUID id) {
        return orderMapper.toOutput(orderService.getById(id));
    }

    @GetMapping
    public Page<OrderDto.Output> page(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        return orderService.page(page, Math.max(limit, 10)).map(orderMapper::toOutput);
    }

    @GetMapping("current")
    public OrderDto.Output getCurrent() {
        return orderMapper.toOutput(orderService.currentOrder());
    }

/*    @PutMapping("{id}")
    public OrderDto.Output update(@PathVariable UUID id, @RequestBody OrderDto.Update orderDto) {
        return orderMapper.toOutput(orderService.update(id, orderDto, orderMapper::toUpdate));
    }*/
}
