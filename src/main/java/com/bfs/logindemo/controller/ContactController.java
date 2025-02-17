package com.bfs.logindemo.controller;

import com.bfs.logindemo.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam String subject,
                                    @RequestParam String email,
                                    @RequestParam String message,
                                    Model model) {

        boolean isSent = contactService.sendMessage(subject, email, message);

        if (isSent) {
            model.addAttribute("success", "Your message has been sent successfully!");
        } else {
            model.addAttribute("error", "Failed to send message. Please try again.");
        }

        return "contact";
    }
}
