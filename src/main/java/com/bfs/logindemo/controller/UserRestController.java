package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // Add a new user
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.createNewUser(user);
        return ResponseEntity.ok("User added successfully");
    }

    // Delete an existing user
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    // Activate or suspend an existing user
    @PatchMapping("/{userId}/status")
    public ResponseEntity<String> toggleUserStatus(@PathVariable int userId, @RequestParam boolean activate) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            userService.updateUserStatus(userId, activate);
            return ResponseEntity.ok("User status updated successfully");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

//    // Get all users
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }
//
//    // Get a user by userId
//    @GetMapping
//    public ResponseEntity<?> getUserById(@RequestParam int userId) {
//        Optional<User> userOptional = userService.getUserById(userId);
//
//        if (userOptional.isPresent()) {
//            return ResponseEntity.ok(userOptional.get());
//        }
//        return ResponseEntity.badRequest().body("User not found");
//    }

    @GetMapping
    public ResponseEntity<Object> getUser(@RequestParam(required = false) Integer userId) {
        if (userId != null) {
            Optional<User> user = userService.getUserById(userId);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            }
            return ResponseEntity.badRequest().body("User not found");
        }
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
