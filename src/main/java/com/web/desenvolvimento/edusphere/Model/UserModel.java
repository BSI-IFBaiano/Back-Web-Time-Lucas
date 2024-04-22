package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends PersonModel {
    private Long idUser;
    private String user;
    private String password;
    private LocalDate createAt;
}
