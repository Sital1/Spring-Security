package com.sital.csrf.config.security;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*

CSRF filter token generates the filter. So, we add this filter after the CSRF filter

 */

public class CsrfTokenLoggerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");;
        System.out.println(token.getToken());
        filterChain.doFilter(request,response);
    }
}
