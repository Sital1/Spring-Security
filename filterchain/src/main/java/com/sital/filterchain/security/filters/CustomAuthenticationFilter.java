package com.sital.filterchain.security.filters;


import com.sital.filterchain.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {


    // inject the authentication manager

  @Autowired
    private AuthenticationManager manager;



    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // get the authorization header
        String authorization = servletRequest.getHeader("Authorization");

        // some logic

        // returns fully authenticated object.. otherwise throw exception

        var auth = new CustomAuthentication(authorization,null);

        try{
            Authentication authenticationResult = manager.authenticate(auth);

            // continue the filter chain

            if (authenticationResult.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authenticationResult);
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }catch (AuthenticationException exception){
            servletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }


    }


}
