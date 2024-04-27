package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrolledStudent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolledStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnrolledStudent;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private UserModel userM;
}
