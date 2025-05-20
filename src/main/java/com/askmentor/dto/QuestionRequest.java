package com.askmentor.dto;

public class QuestionRequest {
    
    private int user_id;
    private String question;
    private Integer status;
    private Integer answerUserId;
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAnswerUserId() {
        return answerUserId;
    }
    public void setAnswerUserId(Integer answerUserId) {
        this.answerUserId = answerUserId;
    }

}
