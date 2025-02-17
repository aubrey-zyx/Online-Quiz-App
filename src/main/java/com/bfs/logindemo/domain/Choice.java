package com.bfs.logindemo.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "choice") // Maps to the database table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_id")
    private Integer choiceId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question; // Relationship with Question entity

    @Column(nullable = false)
    private String description;

    @Column(name = "is_correct")
    private boolean isCorrect;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Choice {
//    private int choiceId;
//    private int questionId;
//    private String description;
//    private boolean isCorrect;
//}