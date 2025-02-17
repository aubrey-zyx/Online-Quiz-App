package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.Category;
import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.CategoryService;
import com.bfs.logindemo.service.QuizService;
import com.bfs.logindemo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/quiz-results")
public class AdminQuizResultController {

    private final QuizService quizService;
    private final CategoryService categoryService;
    private final UserService userService;

    public AdminQuizResultController(QuizService quizService, CategoryService categoryService, UserService userService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public String quizResultManagementPage(@RequestParam(required = false) Integer categoryId,
                                           @RequestParam(required = false) Integer userId,
                                           HttpSession session,
                                           Model model) {
        User admin = (User) session.getAttribute("user");

        // Redirect if not logged in or not an admin
        if (admin == null || !admin.isAdmin()) {
            return "redirect:/login";
        }

        List<Quiz> quizResults = quizService.getFilteredQuizzes(categoryId, userId);
        List<Category> categories = categoryService.getAllCategories();
        List<User> users = userService.getAllUsers();

        model.addAttribute("quizResults", quizResults);
        model.addAttribute("categories", categories);
        model.addAttribute("users", users);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("selectedUserId", userId);

        return "adminQuizResults";
    }
}
