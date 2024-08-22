package com.web.desenvolvimento.edusphere.dto.subjectInCourse;

import jakarta.validation.constraints.NotBlank;

public record SubjectInCourseResponseDTO(
        Long idSubjectInCourse,
        @NotBlank String courseName,
        @NotBlank String subjectName
) {
}
