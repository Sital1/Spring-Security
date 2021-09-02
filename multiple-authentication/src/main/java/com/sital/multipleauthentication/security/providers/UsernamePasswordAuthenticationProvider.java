package com.sital.multipleauthentication.security.providers;


import com.sital.multipleauthentication.security.authentications.UsernamePasswordAuthentication;
import com.sital.multipleauthentication.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private JpaUserDetailsService jpaUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsernamePasswordAuthenticationProvider(JpaUserDetailsService jpaUserDetailsService, PasswordEncoder passwordEncoder) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDetails user = jpaUserDetailsService.loadUserByUsername(username);

        if(user != null){
            if(passwordEncoder.matches(password,user.getPassword())){
                return new UsernamePasswordAuthentication(username,password,user.getAuthorities());
            }
        }

        throw new BadCredentialsException("Username or password is wrong");
    }

    @Override
    public boolean supports(Class<?> aClass) {

        // supprts the username password auth class

        return UsernamePasswordAuthentication.class.equals(aClass);
    }
}
