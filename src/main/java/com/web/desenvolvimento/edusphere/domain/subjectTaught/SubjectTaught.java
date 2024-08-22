package com.web.desenvolvimento.edusphere.domain.subjectTaught;

import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_subjects_taught")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTaught {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject_taught")
    private Long idSubjectTaught;

    @ManyToOne
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

}
