package com.sital.csrf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping
    public String main(){
        return "main.html";
    }

    @PostMapping("/change") // POST, PUT, DELETE for mutating actions in the REST
    public String doSomething(){

        System.out.println("Inside post method");

        return "main.html";

    }

}
