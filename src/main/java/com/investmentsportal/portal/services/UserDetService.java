package com.investmentsportal.portal.services;

import com.investmentsportal.portal.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetService implements UserDetailsService { // authenticate user
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = usersRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("Invalid credentials")
        );
        return new User(
                user.getEmail(), user.getPassword(), Collections.emptyList()
        );
    }

}
