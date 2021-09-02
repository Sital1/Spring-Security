package com.sital.multipleauthentication.service;

import com.sital.multipleauthentication.entities.User;
import com.sital.multipleauthentication.repositories.UserRepository;
import com.sital.multipleauthentication.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// retrieves the user, implement the contract UserDetails Service, loadUserByUserName
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){

       var optionalUser = userRepository.findUsersByUsername(username);

        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));

        // cannot return the above user because haven't implemented the userDetails contract
        // don't make the User entity a userDetails as it will violate the single responsibility principle
        // use the Security user wrapper

        return new SecurityUser(user);
    }
}
