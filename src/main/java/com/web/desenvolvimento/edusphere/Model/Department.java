package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "departament")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartament;

    @ManyToOne
    @JoinColumn(name = "idManager", nullable = false)
    private Manager manager;
    private String name;
    private LocalDateTime createdAt = LocalDateTime.now();
}
