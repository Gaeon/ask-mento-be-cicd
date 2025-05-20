package com.askmentor.dto;

import com.askmentor.model.Question;
import com.askmentor.model.Answer;
import lombok.Data;

import java.util.List;

@Data
public class QuestionWithAnswerResponse {
    private Question question;
    private List<Answer> answers;

    public QuestionWithAnswerResponse(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }
}