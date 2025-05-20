package com.askmentor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.askmentor.dto.QuestionRequest;
import com.askmentor.dto.QuestionWithAnswerResponse;
import com.askmentor.model.Answer;
import com.askmentor.model.Question;
import com.askmentor.service.QuestionService;
import com.askmentor.service.UserService;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	private final QuestionService questionService;
	private final UserService userService;
	
	public QuestionController(QuestionService questionService, UserService userService) {
		this.questionService = questionService;
		this.userService = userService;
	}

	@GetMapping("/{user_id}")
	public ResponseEntity<List<Question>> getUserQuestions(@PathVariable int user_id) {
		List<Question> questions = questionService.getUserQuestions(user_id);
		return ResponseEntity.ok(questions);
	}

	@PostMapping("/{user_id}")
	public ResponseEntity<Map<String, String>> createQuestion(@PathVariable int user_id, @RequestBody QuestionRequest request) {
		String questionResult = questionService.createQuestion(user_id, request);
		String userUpdateResult = userService.updateQuestionCount(user_id);
		return ResponseEntity.ok(Map.of(
			"question", questionResult,
			"update", userUpdateResult
		));
	}

	@GetMapping("/detail/{question_id}")
	public ResponseEntity<QuestionWithAnswerResponse> getQuestionDetail(@PathVariable int question_id) {
		Question question = questionService.getQuestionDetail(question_id);
		List<Answer> answers = questionService.getAnswersByQuestionId(question_id);

		return ResponseEntity.ok(new QuestionWithAnswerResponse(question, answers));
	}

	@PostMapping("/search")
	public ResponseEntity<List<Map<String, String>>> searchSimilarQuestions(@RequestBody Map<String, String> request) {
		List<Map<String, String>> similarQuestions = questionService.searchSimilarQuestions(request);
		return ResponseEntity.ok(similarQuestions);
	}
}