package com.simplecoding.jwt.service;

import com.simplecoding.jwt.model.JwtUser;
import com.simplecoding.jwt.repo.JwtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    JwtUserRepository jwtUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JwtUser jwtUser = jwtUserRepository.findUserByEmail(email);
        if (jwtUser == null) {
            throw new UsernameNotFoundException("email Not found" + email);
        }
        return new User(jwtUser.getEmail(), jwtUser.getPassword(), new ArrayList<>());
    }
}
