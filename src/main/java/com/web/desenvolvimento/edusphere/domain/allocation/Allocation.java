package com.web.desenvolvimento.edusphere.domain.allocation;

import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_allocations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_allocation")
    private Long idAllocation;

    @ManyToOne
    @JoinColumn(name = "id_subject_taught", nullable = false)
    private SubjectTaught subjectTaught;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year_allocation")
    private Long yearAllocation;
}