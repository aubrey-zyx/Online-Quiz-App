package com.bfs.logindemo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "quizquestion") // Maps to the database table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qq_id")
    private Integer qqId;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz; // Many quiz questions belong to one quiz

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question; // Many quiz questions reference one question

    @ManyToOne
    @JoinColumn(name = "user_choice_id")
    private Choice userChoice; // Stores the choice selected by the user
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class QuizQuestion {
//    private int qqId;
//    private int quizId;
//    private int questionId;
//    private int userChoiceId;
//}
