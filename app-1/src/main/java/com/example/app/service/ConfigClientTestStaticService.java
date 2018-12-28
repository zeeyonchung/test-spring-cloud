package com.example.app.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigClientTestStaticService {

    //    @Value("${first}")
    @Value("test1")
    private String first;

    //    @Value("${second}")
    @Value("test2")
    private String second;

    public String getKeyword() {
        return first + second;
    }
}
