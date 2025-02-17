package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String getLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

//         redirect to /quiz if user is already logged in
        if (session != null && session.getAttribute("user") != null) {
            return "redirect:/home";
        }

        return "login";
    }

    // validate that we are always getting a new session after login
    @PostMapping("/login")
    public String postLogin(@RequestParam String email,
                            @RequestParam String password,
                            HttpServletRequest request) {

        Optional<User> possibleUser = loginService.validateLogin(email, password);

        if (possibleUser.isPresent()) {
            HttpSession oldSession = request.getSession(false);
            // Invalidate old session if it exists
            if (oldSession != null) oldSession.invalidate();

            // Generate new session
            HttpSession newSession = request.getSession(true);

            // Store user details in session
            User user = possibleUser.get();
            newSession.setAttribute("user", user);

            // Redirect based on user role
            if (user.isAdmin()) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }
        } else { // If user details are invalid
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, Model model) {
        HttpSession oldSession = request.getSession(false);
        // invalidate old session if it exists
        if(oldSession != null) oldSession.invalidate();
        return "login";
    }
}
