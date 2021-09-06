package com.sital.crossoriginresourcesharing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrossOriginResourceSharingApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrossOriginResourceSharingApplication.class, args);

        /*
            - What happens when app consume multiple origin
              - > origin --> domain
            - CORS -> policy when multiple origin are allowed and which
         */

    }

}
