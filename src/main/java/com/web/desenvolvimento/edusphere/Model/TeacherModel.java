package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherModel extends PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeacher;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private UserModel userr;

    @ManyToOne
    @JoinColumn(name = "idDepartment", nullable = false)
    private DepartmentModel department;

}
