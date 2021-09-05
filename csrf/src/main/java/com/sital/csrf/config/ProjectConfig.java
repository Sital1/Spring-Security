package com.sital.csrf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        // generally don't disable the csrf
        // it comes the default

        // GET, TRACE, HEAD, OPTIONS --> no need to specify any thing special

        // When a page is loaded Spring Security generates a token.

        // Can't call mutating actions POST,PUT,DELETE without csrf token

        // Right now works for Login but not other endpoints cause CSRF token has not been added
       // http.csrf().disable();
    }


}
