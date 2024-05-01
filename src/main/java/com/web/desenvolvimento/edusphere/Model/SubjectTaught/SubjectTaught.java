package com.web.desenvolvimento.edusphere.Model.SubjectTaught;

import com.web.desenvolvimento.edusphere.Model.Subject.Subject;
import com.web.desenvolvimento.edusphere.Model.Teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjectsTaught")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectTaught {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubjectTaught;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

}
