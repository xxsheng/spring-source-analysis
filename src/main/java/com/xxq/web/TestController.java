package com.xxq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/request")
    public String test() {
        return "你好";
    }

    @GetMapping("/test")
    public void test1() {

        String result = restTemplate.getForObject("http://192.168.99.180:8080/test/request", String.class);
        System.out.println("hello");

    }

}
