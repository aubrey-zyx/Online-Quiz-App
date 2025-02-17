package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        // Redirect to login if not logged in or not an admin
        if (user == null || !user.isAdmin()) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "adminHome";
    }
}

