package com.example.app.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RibbonClient(name="app2")
public class CircuitBreakerTestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="fallback_findTestString")
    public String findTestString() {
        String testString = restTemplate.getForObject("http://app2/test-string", String.class);
        return testString;
    }

    @SuppressWarnings("unused")
    public String fallback_findTestString() {
        return "APP1 test string fallback";
    }
}
