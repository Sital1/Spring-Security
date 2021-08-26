package com.sital.springsecurityc2.services;

import com.sital.springsecurityc2.entities.User;
import com.sital.springsecurityc2.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
   private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<User> user = userRepository.findUserByUsername(username);

        User u = user.orElseThrow(()-> new UsernameNotFoundException("Error! "));

        return new SecurityUser(u);
    }
}
