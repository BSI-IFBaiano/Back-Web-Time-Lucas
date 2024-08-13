package com.web.desenvolvimento.edusphere.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequestDTO(Long idDepartment, @NotNull Long idManager, @NotBlank String name, @NotBlank String description) {

}
