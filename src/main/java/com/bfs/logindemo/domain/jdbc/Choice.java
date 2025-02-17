package com.bfs.logindemo.domain.jdbc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int choiceId;
    private int questionId;
    private String description;
    private boolean isCorrect;
}
