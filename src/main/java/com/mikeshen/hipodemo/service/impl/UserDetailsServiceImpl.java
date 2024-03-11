package com.mikeshen.hipodemo.service.impl;

import com.mikeshen.hipodemo.service.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails getUserByUsername(String username) {
        // no repository created yet
        if (username.equals("user")) {
            User user = new User(username, "", Arrays.asList());
            return user;
        }
        return null;
    }
}
