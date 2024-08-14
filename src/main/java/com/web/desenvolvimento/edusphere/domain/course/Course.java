package com.web.desenvolvimento.edusphere.domain.course;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course")
    private Long idCourse;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @Column(name = "name")
    private String name;

    @Column(name = "tot_work_load")
    private int totWorkLoad;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

}
