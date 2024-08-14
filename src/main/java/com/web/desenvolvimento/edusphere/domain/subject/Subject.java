package com.web.desenvolvimento.edusphere.domain.subject;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject")
    private Long idSubject;

    @ManyToOne
    @JoinColumn(name = "id_department", nullable = false)
    private Department department;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tot_number_of_classes", nullable = false)
    private int totNumberOfClasses;

}
