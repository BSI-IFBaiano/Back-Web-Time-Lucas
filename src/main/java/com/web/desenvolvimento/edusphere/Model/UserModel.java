package com.web.desenvolvimento.edusphere.Model;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//@Entity
//@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends PersonModel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String user;
    private String password;
    private LocalDate createAt;
    private String name;
    private String lastName;


}
