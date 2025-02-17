package com.bfs.logindemo.domain;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "quiz") // Maps to the database table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Integer quizId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relationship with User entity

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Relationship with Category entity

    @Column(nullable = false)
    private String name;

    @Column(name = "time_start", nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(nullable = false)
    private int score;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "quizquestion",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Quiz {
//    private int quizId;
//    private int userId;
//    private int categoryId;
//    private String name;
//    private LocalDateTime timeStart;
//    private LocalDateTime timeEnd;
//    private int score;
//}
