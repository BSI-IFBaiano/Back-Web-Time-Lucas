package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjectsTaught")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectsTaught {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubjectsTaught;

    @ManyToOne
    @JoinColumn(name = "idSubject", nullable = false)
    private SubjectModel subject;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

}
