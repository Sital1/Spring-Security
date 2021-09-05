package com.sital.csrf.config;

import com.sital.csrf.config.security.CsrfTokenLoggerFilter;
import com.sital.csrf.config.security.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

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

        // customization
        http.csrf(c->{

            // ignore csrf protection for some endpoints
            c.ignoringAntMatchers("/csrfdisabled/**"); // will be disabled for anythin under /csrfdisabled

            /*

            Default mechanism:
                1. Relies on session id
                2. Generates unique identifiers
                3. Attached to session
                4. Request -> looks to session id, find the token and match the tokem -> approve

             Can customize the csrf mechanism.
             */

            // customization

            //csrfTokenRepository starting point
            c.csrfTokenRepository(new CustomCsrfTokenRepository());

                });

        http.addFilterAfter(new CsrfTokenLoggerFilter(), CsrfFilter.class);

    }


}
