package com.example.taxicompany.service;

import com.example.taxicompany.entity.User;
import com.example.taxicompany.repository.UserRepository;
import com.example.taxicompany.security.PasswordEncoderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;
    public User createUser(User user) {
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }
    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}

