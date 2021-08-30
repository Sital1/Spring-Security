package com.sital.filterchain.security.providers;

import com.sital.filterchain.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {

    @Value("${key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String requestKey = authentication.getName();

        if (requestKey.equals(key)) {
            var auth = new CustomAuthentication(null, null, null);
            return auth;
        } else {
            throw new BadCredentialsException("key not found");

        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomAuthentication.class.equals(aClass);
    }
}
