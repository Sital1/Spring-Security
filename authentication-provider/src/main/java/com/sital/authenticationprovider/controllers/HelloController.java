package com.sital.authenticationprovider.controllers;


import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    public String hello(){
        return "Hello!";
    }
}
