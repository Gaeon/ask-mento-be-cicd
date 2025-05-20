package com.askmentor.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    private int departmentId;
    
    @Column(name = "parent_department")
    private int parentDepartment;
    
    @Column(name = "department")
    private String department;
}