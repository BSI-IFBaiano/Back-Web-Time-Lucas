package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManager;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private User userr;
}
