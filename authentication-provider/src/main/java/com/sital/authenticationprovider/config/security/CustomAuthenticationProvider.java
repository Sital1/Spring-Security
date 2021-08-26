package com.sital.authenticationprovider.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Authentication object represents authentication process, also this represents fully authenticated
                // until decided isAuthenticated is false..
                // when finished and valid and isAuthenticated == true, verified
                 // also has credentials, details about authentication, principal abd authorities


        // here we implement the authentication logic
        // if the request is authenticated, should return here a fully authenticated Authentication instance

        // if the result is not authenticated, should throw AuthenticationException

        // the Authentication is not supported --> return null

        // get username and password from authentication
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials()); // get credentials returns an object

        // load user by username
        // throws authentication expection
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // validation
        if(userDetails != null){
         if(passwordEncoder.matches(password, userDetails.getPassword() ) ){

             var authenticated = new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());

             return authenticated;
         }
        }
        throw new BadCredentialsException("Wrong username and password");


    }


    // this method is called before the above method

    @Override
    public boolean supports(Class<?> authenticationType) { // type of authentication

        // basic auth
        return UsernamePasswordAuthenticationToken.class.equals(authenticationType);
    }
}
