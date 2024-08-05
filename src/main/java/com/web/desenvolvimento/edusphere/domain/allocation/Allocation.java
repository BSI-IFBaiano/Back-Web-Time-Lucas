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
    private Long idAllocation;

    @ManyToOne
    @JoinColumn(name = "idSubjectTaught", nullable = false)
    private SubjectTaught subjectTaught;

    private String semester;
}
