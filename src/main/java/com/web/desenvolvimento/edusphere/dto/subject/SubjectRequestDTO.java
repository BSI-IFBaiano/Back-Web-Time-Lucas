package com.web.desenvolvimento.edusphere.dto.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubjectRequestDTO(
        Long idSubject,
        @NotNull Long idDepartment,
        @NotBlank String name,
        @NotNull int totNumberOfClasses
) {
}
