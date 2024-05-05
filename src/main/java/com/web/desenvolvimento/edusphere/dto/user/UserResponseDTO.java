package com.web.desenvolvimento.edusphere.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserResponseDTO(Long idUser, @NotBlank String login, @NotBlank String email,
                              @NotBlank String phone, @NotNull LocalDateTime createdAt) {
}
