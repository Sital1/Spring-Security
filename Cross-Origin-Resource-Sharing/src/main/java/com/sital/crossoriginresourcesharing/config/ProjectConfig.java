package com.sital.crossoriginresourcesharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().permitAll();

        /*
        Use customizer to customize the origin
        Make different config class in real world applications

        We can set the allowed origins, allowed methods and headers as well.
         */

        http.cors(c -> {
            CorsConfigurationSource cs = r ->{
                CorsConfiguration corsConfiguration = new CorsConfiguration();

                corsConfiguration.setAllowedOrigins(List.of("*"));
                corsConfiguration.setAllowedMethods(List.of("GET","POST"));


                return corsConfiguration;
            };
            c.configurationSource(cs);

        });

    }
}
