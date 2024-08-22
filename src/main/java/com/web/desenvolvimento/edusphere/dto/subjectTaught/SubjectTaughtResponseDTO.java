package com.web.desenvolvimento.edusphere.dto.subjectTaught;

import jakarta.validation.constraints.NotBlank;

public record SubjectTaughtResponseDTO(
    Long idSubjectTaught,
    @NotBlank String subjectName,
    @NotBlank String teacherName
) {
}
