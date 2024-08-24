package com.web.desenvolvimento.edusphere.dto.studentRegistration;

import jakarta.validation.constraints.NotNull;

public record StudentRegistrationRequestDTO(
        Long idStudentRegistration,
        @NotNull Long idStudent,
        @NotNull Long idAllocation
) {
}
