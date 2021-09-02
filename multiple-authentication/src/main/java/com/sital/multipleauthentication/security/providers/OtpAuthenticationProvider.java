package com.sital.multipleauthentication.security.providers;

import com.sital.multipleauthentication.entities.Otp;
import com.sital.multipleauthentication.repositories.OtpRepository;
import com.sital.multipleauthentication.security.authentications.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.Optional;

public class OtpAuthenticationProvider implements AuthenticationProvider {

    private OtpRepository otpRepository;

    @Autowired
    public OtpAuthenticationProvider(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String otp = (String) authentication.getCredentials();

        Optional<Otp> optionalOtp = otpRepository.findOtpByUsername(username);

        if(optionalOtp.isPresent()){
            return new OtpAuthentication(username,otp, List.of(() -> "read" ));
        }

       throw new BadCredentialsException("Otp not valid");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}
