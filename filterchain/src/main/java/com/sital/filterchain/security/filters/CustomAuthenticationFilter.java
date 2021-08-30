package com.sital.filterchain.security.filters;


import com.sital.filterchain.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthenticationFilter implements Filter {


    // inject the authentication manager

  @Autowired
    private AuthenticationManager manager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // cast the filter to http filter
        var http = (HttpServletRequest) servletRequest;

        // get the authorization header
        String authorization = http.getHeader("Authorization");

        // some logic

        // returns fully authenticated object.. otherwise throw exception

        var auth = new CustomAuthentication(authorization,null);

        Authentication authenticationResult = manager.authenticate(auth);

        // continue the filter chain

        if (authenticationResult.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationResult);
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
