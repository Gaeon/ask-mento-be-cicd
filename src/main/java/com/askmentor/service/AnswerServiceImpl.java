package com.askmentor.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.askmentor.dto.AnswerRequest;
import com.askmentor.dto.AnswerUpdateRequest;
import com.askmentor.dto.SatisfactionRequest;
import com.askmentor.model.Answer;
import com.askmentor.model.Question;
import com.askmentor.repository.AnswerRepository;
import com.askmentor.repository.QuestionRepository;


@Service
public class AnswerServiceImpl implements AnswerService {
    
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;  // 추가: QuestionRepository 의존성 주

    // 생성자를 통해 AnswerRepository 의존성 주입
    public AnswerServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    /**
     * 특정 사용자의 모든 답변을 조회합니다.
     * 
     * @param user_id 조회할 사용자의 ID
     * @return 해당 사용자가 작성한 모든 답변 리스트
     */
    @Override
    public List<Map<String, Object>> getUserAnswers(int user_id) {
        List<Answer> answers = answerRepository.findByUserId(user_id);
        return answers.stream().map(answer -> {
            Map<String, Object> result = new HashMap<>();
            result.put("answerId", answer.getAnswerId());
            result.put("questionId", answer.getQuestionId());
            result.put("userId", answer.getUserId());
            result.put("answer", answer.getAnswer());
            result.put("timestamp", answer.getTimestamp());
            result.put("satisfaction", answer.getSatisfaction());
            
            // Question 정보 조회 및 추가
            Question question = questionRepository.findById(answer.getQuestionId()).orElseThrow();
            result.put("question", question.getQuestion());
            result.put("state", question.getStatus());
            
            return result;
        }).collect(Collectors.toList());
    }

    /**
     * 새로운 답변을 생성하여 저장합니다.
     * 
     * @param user_id 답변을 작성하는 사용자 ID
     * @param request AnswerRequest 객체 (질문 ID, 답변 내용, 만족도 포함)
     * @return 성공 메시지
     */
    @Override
    public String createAnswer(int user_id, AnswerRequest request) {
        Answer answer = new Answer();
        answer.setQuestionId(request.getQuestion_id());        // 질문 ID 설정
        answer.setUserId(user_id);                             // 작성자 ID 설정
        answer.setAnswer(request.getAnswer());                 // 답변 내용 설정
        answer.setTimestamp(LocalDateTime.now());
        answer.setSatisfaction(request.getSatisfaction());     // 초기 만족도 설정 (선택 사항)
        answerRepository.save(answer);                         // DB에 저장
        
        return "답변 등록 성공";
        
    }

    @Override
    public int updateAnswer(int answer_id, AnswerUpdateRequest request) {
        // 1. 답변 조회
        Answer answer = answerRepository.findById(answer_id)
        .orElseThrow(() -> new RuntimeException("답변 없음"));

        // 2. 답변 내용 업데이트
        answer.setAnswer(request.getAnswer());                 // 답변 내용 설정
        answer.setTimestamp(LocalDateTime.now());
        answerRepository.save(answer);                         // DB에 저장

        // 3. 관련 질문 상태 변경 (state = 1)
        int questionId = answer.getQuestionId();
        Question question = questionRepository.findById(questionId)
            .orElseThrow(() -> new RuntimeException("질문 없음"));
        question.setStatus(1); 
        questionRepository.save(question);
        
        // 4. 사용자 ID 반환
        return answer.getUserId();
        
    }

    /**
     * 특정 답변의 상세 정보를 조회합니다.
     * 
     * @param answer_id 조회할 답변의 ID
     * @return 해당 답변 객체 (없을 경우 예외 발생)
     */
    @Override
    public Answer getAnswerDetail(int answer_id) {
        return answerRepository.findById(answer_id).orElseThrow();  // 답변 없으면 예외 발생
    }

    /**
     * 특정 답변에 대한 만족도를 등록하거나 수정합니다.
     * 
     * @param answer_id 만족도를 갱신할 답변의 ID
     * @param request SatisfactionRequest 객체 (수정할 만족도 값 포함)
     * @return 성공 메시지
     */
    @Override
    public String updateSatisfaction(int answer_id, SatisfactionRequest request) {
        Answer answer = answerRepository.findById(answer_id).orElseThrow(); // 답변 조회
        answer.setSatisfaction(request.getSatisfaction());                  // 만족도 수정
        answerRepository.save(answer);                                      // DB에 저장
        return "평점 등록/수정 성공";
    }
}
