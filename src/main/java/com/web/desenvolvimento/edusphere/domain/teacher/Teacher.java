package com.web.desenvolvimento.edusphere.domain.teacher;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.person.Person;
import com.web.desenvolvimento.edusphere.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
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
