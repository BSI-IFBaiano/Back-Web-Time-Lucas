package com.web.desenvolvimento.edusphere.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String userr;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createAt = LocalDateTime.now();

}
