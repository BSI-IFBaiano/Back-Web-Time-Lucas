package com.web.desenvolvimento.edusphere.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserDTO(Long idUser, @NotBlank String login, @NotBlank String password, @NotBlank String email,
                      @NotBlank String phone, @NotNull LocalDateTime createdAt) {
}
