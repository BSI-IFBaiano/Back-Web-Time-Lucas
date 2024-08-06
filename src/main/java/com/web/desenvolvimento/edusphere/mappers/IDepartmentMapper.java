package com.web.desenvolvimento.edusphere.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;

@Mapper
public interface IDepartmentMapper {
	
	IDepartmentMapper INSTANCE = Mappers.getMapper(IDepartmentMapper.class);

    Department toModel(DepartmentRequestDTO DepartmentRequestDTO);

    DepartmentResponseDTO toDTO(Department department);
    List<DepartmentResponseDTO> toDTO(List<Department> departments);
	
}
