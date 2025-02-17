package com.bfs.logindemo.domain.jdbc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int questionId;
    private int categoryId;
    private String description;
    private boolean isActive;
}
