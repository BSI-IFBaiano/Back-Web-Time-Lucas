package com.web.desenvolvimento.edusphere.domain.admin;

import com.web.desenvolvimento.edusphere.domain.person.Person;
import com.web.desenvolvimento.edusphere.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAdmin;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;
}
