package com.example.tokenimplementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TokenImplementationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenImplementationApplication.class, args);

        /*
        Opaque tokens are mainly used for verifying and don't have any data in them.

        Verify Token Methods:
             1. Resource Server Calls the authorization server via /check endpoint.
             2. Blackboarding database. A shared database between Resource Server and authorization Server. Usually avoid having shared database.

             Soln only in examples
               Resource Server and Authorization Server are the Same application.

         */

    }

}
