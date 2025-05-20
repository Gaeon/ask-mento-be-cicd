package com.askmentor.service;

import java.util.List;
import java.util.Map;

import com.askmentor.dto.AnswerRequest;
import com.askmentor.dto.AnswerUpdateRequest;
import com.askmentor.dto.SatisfactionRequest;
import com.askmentor.model.Answer;

public interface AnswerService {
    List<Map<String, Object>> getUserAnswers(int user_id);
    String createAnswer(int user_id, AnswerRequest request);
    int updateAnswer(int answer_id, AnswerUpdateRequest request);
    Answer getAnswerDetail(int answer_id);
    String updateSatisfaction(int answer_id, SatisfactionRequest request);
}