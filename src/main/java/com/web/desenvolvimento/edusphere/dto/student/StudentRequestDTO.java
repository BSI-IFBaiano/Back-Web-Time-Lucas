package com.web.desenvolvimento.edusphere.dto.student;

import jakarta.validation.constraints.NotNull;

public record StudentRequestDTO(Long idStudent, @NotNull Long idUser, @NotNull Long idCourse) {
}
