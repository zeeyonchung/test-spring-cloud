package com.example.app.controller;

import com.example.app.domain.Member;
import com.example.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class  MemberController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/member")
    public Object memberIndex(HttpServletRequest req) {
        Map<String, Object> result = new HashMap<>();
        result.put("member", new Member("지연", "zeeuser"));
        result.put("order", orderService.getOrdersByUser(req));

        return result;
    }
}
