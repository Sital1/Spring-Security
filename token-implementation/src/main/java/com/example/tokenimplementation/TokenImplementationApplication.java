package com.example.tokenimplementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TokenImplementationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenImplementationApplication.class, args);

        /*
        Opaque tokens are mainly used for verifying and don't have any data in them.
         */

    }

}
