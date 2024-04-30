package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private User userr;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private Course course;
}
