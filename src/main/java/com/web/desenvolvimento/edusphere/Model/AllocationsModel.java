package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "allocations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllocationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAllocations;

    @ManyToOne
    @JoinColumn(name = "idSubjectsTaught", nullable = false)
    private SubjectsTaughtModel subjectsTaught;

    private String semester;
}
