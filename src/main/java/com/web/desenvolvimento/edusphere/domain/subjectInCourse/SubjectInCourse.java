package com.web.desenvolvimento.edusphere.domain.subjectInCourse;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_subjects_in_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject_in_course")
    private Long idSubjectInCourse;

    @ManyToOne
    @JoinColumn(name = "id_course", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject subject;
}
