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
public class ManagerModel extends PersonModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManager;

    @ManyToOne
    @JoinColumn(name = "idUserr", nullable = false)
    private UserModel userr;
}
