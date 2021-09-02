package com.sital.multipleauthentication.security.filters;

import com.sital.multipleauthentication.security.authentications.TokenAuthentication;
import com.sital.multipleauthentication.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenAuthenticationFilter(@Lazy AuthenticationManager authenticationManager) {

        this.authenticationManager=authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        // i get the token and expect to find

        var token = httpServletRequest.getHeader("Authorization");

        // in a real world example we would have more than one line for authentication. In that case we would delegate the authentication to a manger
        // and the manager would find the provider. ALWAYS respect the framework architecture.

        Authentication auth = new TokenAuthentication(token,null);

        var authentication = authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        // work for all the other except the login
        return  !request.getServletPath().equals("/login");
    }
}
