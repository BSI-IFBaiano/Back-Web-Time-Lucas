package com.web.desenvolvimento.edusphere.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public enum Role {
        STUDENT,
        TEACHER,
        MANAGER,
        ADMIN;
}
