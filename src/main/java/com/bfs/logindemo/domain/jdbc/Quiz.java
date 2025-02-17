package com.bfs.logindemo.domain.jdbc;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int quizId;
    private int userId;
    private int categoryId;
    private String name;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private int score;
}
