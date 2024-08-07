package com.web.desenvolvimento.edusphere.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerRequestDTO;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerResponseDTO;

@Mapper
public interface IManagerMapper {
	
	IManagerMapper INSTANCE = Mappers.getMapper(IManagerMapper.class);

    Manager toModel(ManagerRequestDTO managerRequestDTO);

    ManagerResponseDTO toDTO(Manager manager);
    List<ManagerResponseDTO> toDTO(List<Manager> managers);
}
