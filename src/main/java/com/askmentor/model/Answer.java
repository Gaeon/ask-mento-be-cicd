package com.askmentor.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "answers")
public class Answer {
    @Id
    @Column(name = "answer_id")
    private int answerId;
    
    @Column(name = "question_id")
    private int questionId;
    
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "answer_text", length = 300)
    private String answer;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @Column(name = "satisfaction")
    private Integer satisfaction;
}