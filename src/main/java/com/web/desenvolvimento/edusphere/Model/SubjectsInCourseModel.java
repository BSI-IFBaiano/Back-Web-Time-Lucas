package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjectsInCourse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectsInCourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubjectsInCourse;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private CourseModel course;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private SubjectModel subject;
}
