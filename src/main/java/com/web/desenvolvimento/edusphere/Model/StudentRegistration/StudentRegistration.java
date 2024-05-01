package com.web.desenvolvimento.edusphere.Model.StudentRegistration;

import com.web.desenvolvimento.edusphere.Model.User.User;
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
