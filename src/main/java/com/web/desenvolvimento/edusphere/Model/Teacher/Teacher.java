package com.web.desenvolvimento.edusphere.Model.Teacher;

import com.web.desenvolvimento.edusphere.Model.Department.Department;
import com.web.desenvolvimento.edusphere.Model.Person.Person;
import com.web.desenvolvimento.edusphere.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="teachers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeacher;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idDepartment", nullable = false)
    private Department department;

}
