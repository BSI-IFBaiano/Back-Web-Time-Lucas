package com.web.desenvolvimento.edusphere.Model.Manager;

import com.web.desenvolvimento.edusphere.Model.Person.Person;
import com.web.desenvolvimento.edusphere.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
