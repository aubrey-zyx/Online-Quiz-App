package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    // Handle user registration
    @PostMapping("/register")
    public String handleRegister(@RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 Model model) {

        // Check if email is already registered
        Optional<User> existingUser = userService.getUserByEmail(email);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email is already registered. Try another one.");
            return "register";
        }

        // Create a new user (default: active user, not admin)
        User newUser = new User(0, email, password, firstName, lastName, true, false);
        userService.createNewUser(newUser);

        model.addAttribute("success", "Registration successful! Please login.");
        return "redirect:/login";
    }
}
