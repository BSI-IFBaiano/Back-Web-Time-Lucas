package com.web.desenvolvimento.edusphere.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;


public record UserRequestDTO(Long idUser, @NotBlank String username, @NotBlank String password, @NotBlank String email,
                             @NotBlank String phone, @NotBlank String role) {
}
