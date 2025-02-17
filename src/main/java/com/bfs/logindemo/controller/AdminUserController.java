package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserService userService; // Use existing UserService

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userManagementPage(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");

        // Redirect if not logged in or not an admin
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminUserMgmt";
    }

    @PostMapping("/toggle-status")
    public String toggleUserStatus(@RequestParam int userId) {
        User user = userService.getUserById(userId).orElseThrow(() ->
                new RuntimeException("User not found"));
        userService.updateUserStatus(userId, !user.isActive());
        return "redirect:/admin/users";
    }
}
