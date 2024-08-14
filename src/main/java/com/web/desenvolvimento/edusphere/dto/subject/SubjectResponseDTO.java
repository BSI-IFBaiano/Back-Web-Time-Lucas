package com.web.desenvolvimento.edusphere.dto.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubjectResponseDTO(
        Long idSubject,
        @NotBlank String name,
        @NotBlank String departmentName,
        @NotNull int totNumberOfClasses

) {
}
