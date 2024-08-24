package com.web.desenvolvimento.edusphere.dto.studentRegistration;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRegistrationResponseDTO(
        Long idStudentRegistration,
        @NotNull Long idStudent,
        @NotNull Long idAllocation,
        @NotBlank String studentName,
        @NotBlank String teacherName,
        @NotBlank String semester,
        @NotNull Long yearAllocation
) {
}
