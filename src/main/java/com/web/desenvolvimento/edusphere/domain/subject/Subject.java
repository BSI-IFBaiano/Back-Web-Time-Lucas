package com.web.desenvolvimento.edusphere.domain.subject;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubject;

    @ManyToOne
    @JoinColumn(name = "idDepartment", nullable = false)
    private Department department;

    private String name;
    private int totNumberOfClasses;

}
