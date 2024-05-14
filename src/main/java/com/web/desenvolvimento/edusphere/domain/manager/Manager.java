package com.web.desenvolvimento.edusphere.domain.manager;

import com.web.desenvolvimento.edusphere.domain.person.Person;
import com.web.desenvolvimento.edusphere.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "managers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManager;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
