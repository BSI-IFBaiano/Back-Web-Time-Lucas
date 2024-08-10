package com.web.desenvolvimento.edusphere.dto.department;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentResponseDTO(Long idDepartment, @NotBlank String name, @NotNull Long idManager, @NotBlank Instant createdAt) {

}
