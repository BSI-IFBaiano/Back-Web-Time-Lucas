package com.web.desenvolvimento.edusphere.domain.student;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.domain.person.Person;
import com.web.desenvolvimento.edusphere.domain.user.User;
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
