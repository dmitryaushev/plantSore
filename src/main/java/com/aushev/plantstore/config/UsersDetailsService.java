package com.aushev.plantstore.config;

import com.aushev.plantstore.model.User;
import com.aushev.plantstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UsersDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with email %s not exist", username)));
        return new UserPrincipal(user);
    }
}
