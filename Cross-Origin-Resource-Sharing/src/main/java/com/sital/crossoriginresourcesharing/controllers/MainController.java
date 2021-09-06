package com.sital.crossoriginresourcesharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "main.html";
    }

    /*
    CORS allows the endpoint to be called but not the resource sharing
    ONLY STOP BROWSER FROM CONSUMING THE RESPONSE
    METHOD MIGHT BE CALLED. DEPENDS ON IF PREFLIGHT REQUEST ACCEPTED OR NOT
     */
    @PostMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println(":(");
        return "TEST!";
    }

}
