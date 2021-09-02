package com.sital.multipleauthentication.security.filters;

import com.sital.multipleauthentication.entities.Otp;
import com.sital.multipleauthentication.repositories.OtpRepository;
import com.sital.multipleauthentication.security.authentications.OtpAuthentication;
import com.sital.multipleauthentication.security.authentications.UsernamePasswordAuthentication;
import com.sital.multipleauthentication.security.managers.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    private OtpRepository otpRepository;

    private TokenManager tokenManager;

    @Autowired
    public UsernamePasswordAuthFilter(@Lazy AuthenticationManager authenticationManager, OtpRepository otpRepository, @Lazy TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.otpRepository = otpRepository;
        this.tokenManager = tokenManager;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Step 1: username, passwod
        // step 2: username , otp

        var username = httpServletRequest.getHeader("username");
        var password = httpServletRequest.getHeader("password");
        var otp = httpServletRequest.getHeader("otp");


        // depending on the step we need different authentication object which is implemented through authentication contract

        if (otp == null){
            // step 1
            Authentication auth = new UsernamePasswordAuthentication(username,password);

            // send it to the manager and the manager will find the proper authentication provider
            authenticationManager.authenticate(auth);

            String code = String.valueOf(new Random().nextInt(9999)+ 1000);

            Otp otpEntity = new Otp();

            otpEntity.setUsername(username);
            otpEntity.setOtp(code);
            // we generate an otp
            otpRepository.save(otpEntity);

        }else
        {
            // step 2
            Authentication auth = new OtpAuthentication(username,otp);
            // send it to the manager and the manager will find the proper authentication provider
            authenticationManager.authenticate(auth);

            var token = UUID.randomUUID().toString();

            tokenManager.add(token);

            // we issue a token
            httpServletResponse.setHeader("Authorization", token);
        }


    }

    // Allow us to define rule when we don't want to be enabled at run time
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
       // only active if request path /login
        // want the filter to be enabled for login show using the negation
        return  !request.getServletPath().equals("/login");
    }
}
