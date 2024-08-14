package com.web.desenvolvimento.edusphere.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseResponseDTO(Long idCourse, @NotBlank String name, @NotNull int totWorkLoad,
                                @NotBlank String departmentName) {
}
