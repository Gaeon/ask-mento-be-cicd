package com.askmentor.service;

import com.askmentor.model.User;
import com.askmentor.dto.UserUpdateRequest;
import com.askmentor.repository.UserRepository;


import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User getUser(int user_id) {
        return userRepository.findById(user_id).orElseThrow();
    }
    
    @Override
    public String updateUser(int user_id, UserUpdateRequest request) {
        User user = userRepository.findById(user_id).orElseThrow();
        user.setPassword(request.getPassword());
        userRepository.save(user);
        return "사용자 비밀번호 수정 성공";
    }

    @Override
    public String updateQuestionCount(int user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        Integer questionCount = user.getQuestionCount();
        if (questionCount == null) {
            questionCount = 0;
        }
        questionCount++;
        user.setQuestionCount(questionCount);
        userRepository.save(user);
        return "질문수 업데이트 성공";
    }

    @Override
    public String updateAnswerCount(int user_id) {
        User user = userRepository.findById(user_id).orElseThrow();
        Integer answerCount = user.getAnswerCount();
        if (answerCount == null) {
            answerCount = 0;
        }
        answerCount++;
        user.setAnswerCount(answerCount);
        userRepository.save(user);
        return "답변수 업데이트 성공";
    }

    @Override
    public String updateSumSatisfaction(int user_id, int satisfaction) {
        User user = userRepository.findById(user_id).orElseThrow();
        // 받음
        Integer sumSatisfaction = user.getSumSatisfaction();
        if (sumSatisfaction == null) {
            sumSatisfaction = 0;
        }
        sumSatisfaction += satisfaction;
        user.setSumSatisfaction(sumSatisfaction);
        userRepository.save(user);
        return "평점 합계 업데이트 성공";
    }


}