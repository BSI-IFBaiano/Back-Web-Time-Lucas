package com.web.desenvolvimento.edusphere.domain.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {
    private String name;
    private String lastName;

}
