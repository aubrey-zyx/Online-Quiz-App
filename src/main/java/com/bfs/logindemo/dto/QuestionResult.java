package com.bfs.logindemo.dto;

import com.bfs.logindemo.domain.Choice;
import com.bfs.logindemo.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QuestionResult {
    private Question question;
    private List<Choice> choices;
    private Choice userChoice;
    private Choice correctChoice;
}

