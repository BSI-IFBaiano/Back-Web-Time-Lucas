package com.web.desenvolvimento.edusphere.domain.department;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartment;

    @ManyToOne
    @JoinColumn(name = "idManager", nullable = false)
    private Manager manager;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();
}
