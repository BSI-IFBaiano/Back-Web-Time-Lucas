package com.web.desenvolvimento.edusphere.domain.manager;

import com.web.desenvolvimento.edusphere.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_managers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_manager")
    private Long idManager;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
