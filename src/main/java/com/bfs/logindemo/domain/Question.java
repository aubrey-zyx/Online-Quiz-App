package com.bfs.logindemo.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "question") // Maps to the database table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Integer questionId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // Relationship with Category entity

    @Column(nullable = false)
    private String description;

    @Column(name = "is_active")
    private boolean isActive;
}

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Question {
//    private int questionId;
//    private int categoryId;
//    private String description;
//    private boolean isActive;
//}
