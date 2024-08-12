package com.web.desenvolvimento.edusphere.dto.manager;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.domain.user.User;

import jakarta.validation.constraints.NotBlank;

public record ManagerResponseDTO(Long idManager, String name, String email, String role) {

}
