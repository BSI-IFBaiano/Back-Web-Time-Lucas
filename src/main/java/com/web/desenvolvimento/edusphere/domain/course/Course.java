package com.web.desenvolvimento.edusphere.domain.course;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCourse;

    @ManyToOne
    @JoinColumn(name = "idDepartment", nullable = false)
    private Department department;
    private String name;
    private int totWorkLoad;
    private LocalDateTime createdAt = LocalDateTime.now();

}
