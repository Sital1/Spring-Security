package com.sital.springsecurityc2.config;

import com.sital.springsecurityc2.services.JPAUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    // User Detail Service --> Used by Spring to retrieve the user.

    // User Detail Manager --> The object that manages the User. Not just create the user.

    @Bean
    public UserDetailsService userDetailsService(){

        return new JPAUserDetailsService();
    }


}
