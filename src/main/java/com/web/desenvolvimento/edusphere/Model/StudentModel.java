package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class StudentModel extends PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private UserModel userr;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private CourseModel course;
}
