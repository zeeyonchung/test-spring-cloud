package com.example.app.controller;

import com.example.app.domain.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @RequestMapping("/user")
    public List<Order> getOrdersByUser() {
        List<Order> result = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Order temp = new Order(i, "order name");
            result.add(temp);
        }

        return result;
    }
}
