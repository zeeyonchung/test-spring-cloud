package com.example.app.controller;

import com.example.app.domain.Member;
import com.example.app.service.MemberService;
import com.example.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class  MemberController {

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/member")
    public Object memberIndex(HttpServletRequest req, Pageable pageable) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> userMap = new HashMap<>();

        result.put("member", memberService.findByIdGreaterThanEqual(576780, pageable));
        result.put("order", orderService.getOrdersByUser(req));

        return result;
    }
}
