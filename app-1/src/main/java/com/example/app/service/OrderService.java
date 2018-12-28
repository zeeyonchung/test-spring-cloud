package com.example.app.service;

import com.example.app.config.RibbonConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Service
@RibbonClient(name="app2", configuration= RibbonConfig.class)
public class OrderService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="fallbackGetOrdersByUser",
            threadPoolKey = "licenseByOrgThreadPool",
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                            @HystrixProperty(name="maxQueueSize", value="10"),
                    },
            commandProperties={
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")}
    )
    public Object[] getOrdersByUser(HttpServletRequest req) {
        String url = "http://api-gateway/a2/orders/user";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", req.getHeader("Authorization"));

        ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(headers), Object[].class);
        Object[] result = responseEntity.getBody();
        return result;
    }

    @SuppressWarnings("unused")
    public Object[] fallbackGetOrdersByUser(HttpServletRequest req) {
        return new Object[]{"APP1 주문 가져오기 fallback"};
    }
}
