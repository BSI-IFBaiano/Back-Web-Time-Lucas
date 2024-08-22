package com.web.desenvolvimento.edusphere.dto.subjectTaught;

import jakarta.validation.constraints.NotNull;

public record SubjectTaughtRequestDTO(
        Long idSubjectTaught,
        @NotNull Long idTeacher,
        @NotNull Long idSubject
) {
}
