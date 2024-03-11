package com.mikeshen.hipodemo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {
    public UserDetails getUserByUsername(String username);
}
