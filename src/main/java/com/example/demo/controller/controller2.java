package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller2 {

    @RequestMapping("/ii")
    public String createOrder(@RequestParam int num) throws Exception{
        return "库库存成功";
    }
}
