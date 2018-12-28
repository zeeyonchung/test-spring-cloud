package com.example.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value="/test-string")
    public String getTestString() {
        return "APP2 test string (๑•̀ㅂ•́)و✧";
    }
}
