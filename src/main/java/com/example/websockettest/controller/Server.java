package com.example.websockettest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Server {

    @GetMapping("/index")
    public String index(){

        return "/index";
    }

}
