package com.example.springboot_backend.service;

import com.example.springboot_backend.model.User;
import com.example.springboot_backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    // Create a user
    public User createUser(User user) throws DataIntegrityViolationException {
        return userRepository.save(user);
    }

    // Update a user
    public User updateUser(int id, User user) {
        if (userRepository.existsById(id)) {
            user.setUserID(id);
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    // Delete a user
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Authenticate user (for login)
    public Optional<User> authenticateUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    // Get a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get a user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getUsersByBalance(BigDecimal balance) {
        return userRepository.findByBalance(balance);
    }
}

