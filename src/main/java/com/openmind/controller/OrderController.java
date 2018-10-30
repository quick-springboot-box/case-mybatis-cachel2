package com.openmind.controller;

import com.openmind.domain.Order;
import com.openmind.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @RequestMapping(value = "get/{id}")
    public Order getOrder(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }
}
