package com.web.desenvolvimento.edusphere.dto.manager;


import jakarta.validation.constraints.NotNull;

public record ManagerRequestDTO(Long idManager, @NotNull Long idUser) {

}
