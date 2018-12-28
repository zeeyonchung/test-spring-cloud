package com.example.app.controller;

import com.example.app.service.CircuitBreakerTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/hystrix-test")
public class CircuitBreakerTestController {

    @Autowired
    CircuitBreakerTestService circuitBreakerTestService;

    @GetMapping(value="/test-string")
    public String getTestString (Model model) {
        return circuitBreakerTestService.findTestString();
    }
}
