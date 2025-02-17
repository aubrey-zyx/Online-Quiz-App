package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.Category;
import com.bfs.logindemo.domain.Quiz;
import com.bfs.logindemo.domain.User;
import com.bfs.logindemo.service.CategoryService;
import com.bfs.logindemo.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private final QuizService quizService;
    private final CategoryService categoryService;

    public HomeController(QuizService quizService, CategoryService categoryService) {
        this.quizService = quizService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String getHome(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";  // Redirect to login if user isn't logged in
        }

        // Fetch categories and recent quizzes
        List<Category> categories = categoryService.getAllCategories();
        List<Quiz> recentQuizzes = quizService.getUserQuizzes(user.getId());

        model.addAttribute("categories", categories);
        model.addAttribute("recentQuizzes", recentQuizzes);

        return "home";
    }
}
