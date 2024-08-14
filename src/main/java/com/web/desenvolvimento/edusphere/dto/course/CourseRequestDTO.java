package com.web.desenvolvimento.edusphere.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseRequestDTO(Long idCourse, @NotNull Long idDepartment, @NotBlank String name,
                               @NotNull int totWorkLoad) {
}
