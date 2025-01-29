package com.example.musicn.service;

import com.example.musicn.entity.User;
import com.example.musicn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(String username, String password) {
        System.out.println("Registering user: " + username); // Add this for debugging

        if (userRepository.findByUsername(username).isPresent()) {
            return false; // Username already exists
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}
