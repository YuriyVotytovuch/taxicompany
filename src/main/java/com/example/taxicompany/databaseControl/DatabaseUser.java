package com.example.taxicompany.databaseControl;

import com.example.taxicompany.entity.User;
import com.example.taxicompany.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@AllArgsConstructor
@NoArgsConstructor
@Configuration
public class DatabaseUser {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setEnabled(true);
        userRepository.save(user);
    }
}