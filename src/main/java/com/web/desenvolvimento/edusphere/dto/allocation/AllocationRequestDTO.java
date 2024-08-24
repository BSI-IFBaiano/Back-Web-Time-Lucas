package com.web.desenvolvimento.edusphere.dto.allocation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AllocationRequestDTO(
        Long idAllocation,
        @NotNull Long idSubjectTaught,
        @NotBlank String semester,
        @NotNull Long yearAllocation
) {
}
