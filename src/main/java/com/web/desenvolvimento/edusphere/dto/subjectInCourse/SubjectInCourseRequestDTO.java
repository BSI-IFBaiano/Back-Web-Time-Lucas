package com.web.desenvolvimento.edusphere.dto.subjectInCourse;

import jakarta.validation.constraints.NotNull;

public record SubjectInCourseRequestDTO(
        Long idSubjectInCourse,
        @NotNull Long idCourse,
        @NotNull Long idSubject
) {
}
