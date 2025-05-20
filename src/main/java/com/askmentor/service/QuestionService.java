package com.askmentor.service;

import java.util.List;
import java.util.Map;

import com.askmentor.dto.QuestionRequest;
import com.askmentor.model.Answer;
import com.askmentor.model.Question;

public interface QuestionService {
    List<Question> getUserQuestions(int user_id);
    String createQuestion(int user_id, QuestionRequest request);
    Question getQuestionDetail(int question_id);
    List<Answer> getAnswersByQuestionId(int question_id);

    List<Map<String, String>> searchSimilarQuestions(Map<String, String> request);
}