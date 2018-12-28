package com.example.app.controller;

import com.example.app.service.ConfigClientTestDynamicService;
import com.example.app.service.ConfigClientTestStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/config-test")
public class ConfigClientTestController {

    @Autowired
    ConfigClientTestStaticService configClientTestStaticService;

    @Autowired
    ConfigClientTestDynamicService configClientTestDynamicService;

    @GetMapping(value="/static-refresh")
    public String getKeywordsFromConfigServerByStatic (Model model) {
        model.addAttribute("keywords", configClientTestStaticService.getKeyword());
        return "check-keywords";
    }

    @GetMapping(value="/dynamic-refresh")
    public String getKeywordsFromConfigServerByDynamic (Model model) {
        model.addAttribute("keywords", configClientTestDynamicService.getKeyword());
        return "check-keywords";
    }
}
