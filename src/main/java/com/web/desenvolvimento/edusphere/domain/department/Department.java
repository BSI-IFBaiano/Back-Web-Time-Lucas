package com.web.desenvolvimento.edusphere.domain.department;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department")
    private Long idDepartment;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "idManager", nullable = false)
    private Manager manager;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
}
