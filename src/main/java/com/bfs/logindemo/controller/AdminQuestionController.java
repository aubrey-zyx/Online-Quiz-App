package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.Category;
import com.bfs.logindemo.domain.Choice;
import com.bfs.logindemo.domain.Question;
import com.bfs.logindemo.service.CategoryService;
import com.bfs.logindemo.service.ChoiceService;
import com.bfs.logindemo.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/questions")
public class AdminQuestionController {

    private final QuestionService questionService;
    private final ChoiceService choiceService;
    private final CategoryService categoryService;

    public AdminQuestionController(QuestionService questionService, ChoiceService choiceService, CategoryService categoryService) {
        this.questionService = questionService;
        this.choiceService = choiceService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String questionManagementPage(HttpSession session, Model model) {
        List<Question> questions = questionService.getAllQuestions();
        model.addAttribute("questions", questions);
        return "adminQuestionMgmt";
    }

    @PostMapping("/toggle-status")
    public String toggleQuestionStatus(@RequestParam int questionId) {
        questionService.toggleQuestionStatus(questionId);
        return "redirect:/admin/questions";
    }

    @GetMapping("/add")
    public String addQuestionPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("question", new Question());
        return "adminQuestionAdd";
    }

    @PostMapping("/add")
    public String addNewQuestion(@RequestParam String description,
                                 @RequestParam int categoryId,
                                 @RequestParam List<String> choices,
                                 @RequestParam int correctChoice) {
        questionService.addQuestion(description, categoryId, choices, correctChoice);
        return "redirect:/admin/questions";
    }

    @GetMapping("/edit/{id}")
    public String editQuestionPage(@PathVariable int id, Model model) {
        Question question = questionService.getQuestionById(id);
        List<Choice> choices = choiceService.getChoicesForQuestion(id);
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("question", question);
        model.addAttribute("choices", choices);
        model.addAttribute("categories", categories);
        return "adminQuestionEdit";
    }

    @PostMapping("/edit")
    public String updateQuestion(@RequestParam int questionId,
                                 @RequestParam String description,
                                 @RequestParam int categoryId,
                                 @RequestParam List<String> choices,
                                 @RequestParam int correctChoice) {
        questionService.updateQuestion(questionId, description, categoryId, choices, correctChoice);
        return "redirect:/admin/questions";
    }
}
