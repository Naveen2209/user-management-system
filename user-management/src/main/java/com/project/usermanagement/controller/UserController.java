package com.project.usermanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.usermanagement.pojo.User;
import com.project.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody String requestBody) {
        try {
            User user = objectMapper.readValue(requestBody, User.class);
            Integer userId = userService.createUser(user);
            if (userId == -1) {
                return new ResponseEntity<>("Failed to create user", HttpStatus.NOT_IMPLEMENTED);
            }
            return new ResponseEntity<>("User created successfully " + userId, HttpStatus.CREATED);
        } catch (IOException e) {
            System.out.println("Failed to create user" + e);
            return new ResponseEntity<>("Failed to create user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable int id, @RequestBody String requestBody) {
        try {
            User user = objectMapper.readValue(requestBody, User.class);
            user.setId(id);
            Integer result = userService.updateUser(user);
            if (result == -1) {
                return new ResponseEntity<>("Failed to update user", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        try {
            Integer result = userService.deleteUser(id);
            if (result == -1) {
                return new ResponseEntity<>("Failed to delete user", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = userService.listUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody String requestBody) {
        try {
            User user = objectMapper.readValue(requestBody, User.class);
            User loggedInUser = userService.login(user.getEmail(), user.getPassword());
            if (loggedInUser != null) {
                return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
