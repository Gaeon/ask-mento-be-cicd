package com.askmentor.repository;

import com.askmentor.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findByUserId(int user_id);
    List<Answer> findByQuestionId(int question_id);
}