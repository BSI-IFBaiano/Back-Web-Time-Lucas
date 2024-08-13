package com.web.desenvolvimento.edusphere.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeacherRequestDTO(Long idTeacher, @NotNull Long idUser, @NotNull Long idDepartment, @NotBlank String graduation) {
}
