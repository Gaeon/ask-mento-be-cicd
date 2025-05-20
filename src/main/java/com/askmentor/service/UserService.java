package com.askmentor.service;

import com.askmentor.model.User;
import com.askmentor.dto.UserUpdateRequest;

public interface UserService {
    User getUser(int user_id);
    String updateUser(int user_id, UserUpdateRequest request);
    String updateQuestionCount(int user_id);
    String updateAnswerCount(int user_id);
    String updateSumSatisfaction(int user_id, int satisfaction);
}