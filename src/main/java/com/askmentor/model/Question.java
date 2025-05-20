package com.askmentor.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;
    
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "question_text", length = 300)
    private String question;
    
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    
    @Column(name = "status")
    private Integer status;
}