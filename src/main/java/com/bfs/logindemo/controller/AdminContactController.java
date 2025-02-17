package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.Contact;
import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/contacts")
public class AdminContactController {

    private final ContactService contactService;

    public AdminContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String contactManagementPage(HttpSession session, Model model) {
        User admin = (User) session.getAttribute("user");

        // Redirect if not logged in or not an admin
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        List<Contact> contacts = contactService.getAllMessages();
        model.addAttribute("contacts", contacts);
        return "adminContactMgmt";
    }
}
