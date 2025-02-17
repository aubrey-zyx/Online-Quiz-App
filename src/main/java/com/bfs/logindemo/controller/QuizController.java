package com.bfs.logindemo.controller;

import com.bfs.logindemo.domain.*;
import com.bfs.logindemo.dto.QuestionResult;
import com.bfs.logindemo.service.ChoiceService;
import com.bfs.logindemo.service.QuestionService;
import com.bfs.logindemo.service.QuizQuestionService;
import com.bfs.logindemo.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final QuestionService questionService;
    private final ChoiceService choiceService;
    private final QuizQuestionService quizQuestionService;

    public QuizController(QuizService quizService, QuestionService questionService,
                          ChoiceService choiceService, QuizQuestionService quizQuestionService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.choiceService = choiceService;
        this.quizQuestionService = quizQuestionService;
    }

    // Start a new quiz
    @GetMapping("/start/{categoryId}")
    public String startQuiz(@PathVariable int categoryId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        // Check if the user has an ongoing quiz
        Optional<Quiz> ongoingQuiz = quizService.getOngoingQuizByUser(user.getId());
        if (ongoingQuiz.isPresent()) {
            return "redirect:/quiz/continue/" + ongoingQuiz.get().getQuizId();
        }

        // No ongoing quiz, create a new one
        int quizId = quizService.createQuiz(user.getId(), categoryId, "New Quiz");

        // Update quiz name to include the quiz ID
        String quizName = "Quiz #" + quizId;
        quizService.updateQuizName(quizId, quizName);

        // Fetch 5 random questions for the selected category
        List<Question> questions = questionService.getRandomQuestions(categoryId);

        // Store selected questions in the QuizQuestion table
        quizQuestionService.insertQuizQuestions(quizId, questions.stream()
                .map(Question::getQuestionId).collect(Collectors.toList()));

        model.addAttribute("quizId", quizId);
        model.addAttribute("questions", questions);
        model.addAttribute("choiceService", choiceService);
        return "quiz";
    }

    @PostMapping("/submit")
    public String submitQuiz(@RequestParam int quizId,
                             @RequestParam Map<String, String> userChoices) {
        int score = 0;

        for (Map.Entry<String, String> entry : userChoices.entrySet()) {
            // Extract question ID from parameter name (e.g., "userChoices_101" → 101)
            if (!entry.getKey().startsWith("userChoices_")) continue; // Ignore unrelated params
            int questionId = Integer.parseInt(entry.getKey().split("_")[1]);
            int choiceId = Integer.parseInt(entry.getValue());

            // Update user answer in QuizQuestion table
            quizQuestionService.updateUserChoices(quizId, questionId, choiceId);

            // Check if the selected choice is correct
            boolean isCorrect = choiceService.getChoicesForQuestion(questionId)
                    .stream()
                    .filter(choice -> choice.getChoiceId() == choiceId)
                    .findFirst()
                    .map(Choice::isCorrect)
                    .orElse(false);

            if (isCorrect) score++;
        }

        // Update quiz score
        quizService.updateQuizScore(quizId, score);

        return "redirect:/quiz/result/" + quizId;
    }

    @GetMapping("/result/{quizId}")
    public String getQuizResult(@PathVariable int quizId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        // Fetch quiz details
        Quiz quiz = quizService.getQuizById(quizId);

        // Determine Pass/Fail (More than 3 correct answers)
        boolean isPassed = quiz.getScore() > 3;

        // Fetch quiz questions & user answers
        List<QuizQuestion> quizQuestions = quizQuestionService.getQuizQuestions(quizId);

        // Attach choices & correct answer details
        List<QuestionResult> questionResults = new ArrayList<>();
        for (QuizQuestion quizQuestion : quizQuestions) {
            Question question = quizQuestion.getQuestion();
            List<Choice> choices = choiceService.getChoicesForQuestion(question.getQuestionId());

            // Find user selected choice & correct choice
            Choice userChoice = quizQuestion.getUserChoice();

            Choice correctChoice = choices.stream()
                    .filter(Choice::isCorrect)
                    .findFirst()
                    .orElse(null);

            questionResults.add(new QuestionResult(question, choices, userChoice, correctChoice));
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("isPassed", isPassed);
        model.addAttribute("questionResults", questionResults);

        return "quizResult";
    }

    @GetMapping("/continue/{quizId}")
    public String continueQuiz(@PathVariable int quizId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/login";

        // Fetch quiz details
        Quiz quiz = quizService.getQuizById(quizId);
        List<QuizQuestion> quizQuestions = quizQuestionService.getQuizQuestions(quizId);

        model.addAttribute("quizId", quizId);
        model.addAttribute("questions", quizQuestions.stream().map(QuizQuestion::getQuestion).collect(Collectors.toList()));
        model.addAttribute("choiceService", choiceService);
        return "quiz";
    }
}

