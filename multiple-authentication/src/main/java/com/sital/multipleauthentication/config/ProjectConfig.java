package com.sital.multipleauthentication.config;

import com.sital.multipleauthentication.security.filters.UsernamePasswordAuthFilter;
import com.sital.multipleauthentication.security.providers.OtpAuthenticationProvider;
import com.sital.multipleauthentication.security.providers.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {


    private UsernamePasswordAuthenticationProvider authenticationProvider;

    private OtpAuthenticationProvider otpAuthenticationProvider;

    private UsernamePasswordAuthFilter filter;

    @Autowired
    public ProjectConfig(UsernamePasswordAuthenticationProvider authenticationProvider, OtpAuthenticationProvider otpAuthenticationProvider,
                         UsernamePasswordAuthFilter filter) {
        this.authenticationProvider = authenticationProvider;
        this.otpAuthenticationProvider = otpAuthenticationProvider;
        this.filter = filter;
    }



    // override the methods
    // userDetail service
    // Password Encoder
    // separate filter  -> because we are using multiple authentication
    // separate authentication
    // providers as well

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
      auth.authenticationProvider(otpAuthenticationProvider)
              .authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(filter,
                BasicAuthenticationFilter.class
        );
    }
}
