package com.web.desenvolvimento.edusphere.domain.studentRegistration;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;
import com.web.desenvolvimento.edusphere.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "studentRegistrations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_registration")
    private Long idStudentRegistration;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_allocation", nullable = false)
    private Allocation allocation;
}
