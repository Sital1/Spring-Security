package com.sital.multipleauthentication.controllers;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @Async // Maekes the endpoint asynchronous. Multiplethread?
    public String hello(){

        // We can get the authentication anywhere in the application using the security context
        // works in the standard one thread per request
        // Security Context is linked to the thread that created it.
        // a new thread is created in because of the @Async and the thread doesn't have the security context
        //default is THREAD_LOCAL which localizes the security context in the thread.

        // We can change the mode of or use the DelegatingRunnable or DelegatingExecutor
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "Hello!!" + authentication.getName();
    }

}
