package com.askmentor.dto;

public class AnswerRequest {
    private int question_id;
    private int user_id;
    private String answer;
    private Integer satisfaction;
    
    public int getQuestion_id() {
        return question_id;
    }
    
    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public Integer getSatisfaction() {
        return satisfaction;
    }
    
    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }
}