// src/main/java/com/example/userservice/controller/UserController.java
package com.example.userservice.controller;

import com.example.userservice.logging.LogUserRequest;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LogManager.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @LogUserRequest
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/count")
    @LogUserRequest
    public Integer getCountOfUsers() {
        return userRepository.findAll().size();
    }

    @PostMapping
    @LogUserRequest
    public User createUser(@RequestBody User user) {
        LOG.log(Level.INFO, "Create User Triggered");
        LOG.log(Level.DEBUG, "Create User Debug Triggered");
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    @LogUserRequest
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
