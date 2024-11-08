package com.trading3.trading.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {

   @GetMapping
    public String home(){
        return "welcome to Trading Website";
    }

    @GetMapping("/api")
    public String secure(){
        return "welcome to Trading Website Secure";
    }
}
