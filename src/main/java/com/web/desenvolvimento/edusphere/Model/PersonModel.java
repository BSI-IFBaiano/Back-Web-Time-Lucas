package com.web.desenvolvimento.edusphere.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PersonModel {
    private String name;
    private String lastName;

}
