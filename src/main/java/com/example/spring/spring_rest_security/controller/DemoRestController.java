package com.example.spring.spring_rest_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController 
@RequestMapping("/test")
public class DemoRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
