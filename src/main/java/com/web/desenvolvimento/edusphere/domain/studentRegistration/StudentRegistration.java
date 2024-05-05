package com.web.desenvolvimento.edusphere.domain.studentRegistration;

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
    private Long idStudentRegistration;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
