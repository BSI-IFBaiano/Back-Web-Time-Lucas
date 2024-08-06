package com.web.desenvolvimento.edusphere.dto.manager;

import com.web.desenvolvimento.edusphere.domain.user.User;

import jakarta.validation.constraints.NotBlank;

public record ManagerRequestDTO(Long idManager, @NotBlank User user) {

}
