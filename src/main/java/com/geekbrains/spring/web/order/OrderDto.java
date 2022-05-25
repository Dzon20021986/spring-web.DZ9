package com.geekbrains.spring.web.order;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface OrderDto {

    @GetMapping
    List<OrderDto> getOrderList();

    @GetMapping("/{orderId}")
    ResponseEntity<?> getOrder(@PathVariable("orderid") Long id);

    @PostMapping()
    ResponseEntity<?> handlePost(@Validated @RequestBody OrderDto orderDto);

    @PutMapping("/{orderId}")
    ResponseEntity<?> handleUpdate(@PathVariable("orderId") Long id, @Validated @RequestBody OrderDto orderDto);


    @DeleteMapping("/{orderId}")
    void deleteById(@PathVariable("orderId") Long id);

}
