package com.web.desenvolvimento.edusphere.dto.department;

import java.time.Instant;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;

import jakarta.validation.constraints.NotBlank;

public record DepartmentResponseDTO(Long IdDepartment, @NotBlank String name, @NotBlank Manager manager, @NotBlank Instant createdAt) {

}
