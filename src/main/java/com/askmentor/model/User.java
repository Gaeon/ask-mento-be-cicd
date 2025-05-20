package com.askmentor.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private int userId;
    
    @Column(name = "department_id")
    private int departmentId;
    
    @Column(name = "name", length = 10)
    private String name;
    
    @Column(name = "password", length = 20)
    private String password;
    
    @Column(name = "question_count")
    private Integer questionCount;
    
    @Column(name = "answer_count")
    private Integer answerCount;
    
    @Column(name = "sum_satisfaction")
    private Integer sumSatisfaction;
    
    @Column(name = "join_year")
    private LocalDate joinYear;
}