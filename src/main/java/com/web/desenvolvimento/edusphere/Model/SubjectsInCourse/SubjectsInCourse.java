package com.web.desenvolvimento.edusphere.Model.SubjectsInCourse;

import com.web.desenvolvimento.edusphere.Model.Course.Course;
import com.web.desenvolvimento.edusphere.Model.Subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjectsInCourse")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectsInCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubjectInCourse;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private Subject subject;
}
