package com.bfs.logindemo.domain.jdbc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizQuestion {
    private int qqId;
    private int quizId;
    private int questionId;
    private int userChoiceId;
}
