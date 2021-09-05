package com.sital.csrf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsrfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsrfApplication.class, args);
    }


    // Without CSRF attacker can force us to do us action on the application form other source but the application
    // Can delete the data, change pw, sth.

    /*
    Some architecture doesn't need CSRF. For example OAUTH2
     */

}
