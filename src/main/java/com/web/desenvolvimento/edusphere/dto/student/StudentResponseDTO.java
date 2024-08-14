package com.web.desenvolvimento.edusphere.dto.student;

import jakarta.validation.constraints.NotBlank;

public record StudentResponseDTO(
        Long idStudent,
        @NotBlank String studentName,
        @NotBlank String email,
        @NotBlank String courseName
) {
}
