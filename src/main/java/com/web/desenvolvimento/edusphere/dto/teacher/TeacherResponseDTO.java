package com.web.desenvolvimento.edusphere.dto.teacher;

import jakarta.validation.constraints.NotBlank;

public record TeacherResponseDTO(Long idTeacher, @NotBlank String teacherName,
                                 @NotBlank String graduation, @NotBlank String departmentName,
                                 @NotBlank String email) {
}
