package com.web.desenvolvimento.edusphere.dto.allocation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AllocationResponseDTO(
        Long idAllocation,
        @NotBlank String teacherName,
        @NotBlank String subjectName,
        @NotBlank String semester,
        @NotNull Long yearAllocation
) {
}
