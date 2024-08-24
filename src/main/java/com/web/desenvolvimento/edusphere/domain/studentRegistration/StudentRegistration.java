package com.web.desenvolvimento.edusphere.domain.studentRegistration;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;

import com.web.desenvolvimento.edusphere.domain.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_student_registrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_registration")
    private Long idStudentRegistration;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_allocation", nullable = false)
    private Allocation allocation;
}
