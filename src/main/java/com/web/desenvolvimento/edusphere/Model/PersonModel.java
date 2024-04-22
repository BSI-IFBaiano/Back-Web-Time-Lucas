package com.web.desenvolvimento.edusphere.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PersonModel {
    private String name;
    private String lastName;
    private String CPF;
    private String RG;
    private LocalDate dateOfBirth;
    private String email;
}
