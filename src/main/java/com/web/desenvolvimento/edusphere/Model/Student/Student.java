package com.web.desenvolvimento.edusphere.Model.Student;

import com.web.desenvolvimento.edusphere.Model.Course.Course;
import com.web.desenvolvimento.edusphere.Model.Person.Person;
import com.web.desenvolvimento.edusphere.Model.User.User;
import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private Course course;
}
