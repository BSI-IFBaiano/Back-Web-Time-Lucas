package com.web.desenvolvimento.edusphere.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;

@Mapper
public interface IDepartmentMapper {
	
	IDepartmentMapper INSTANCE = Mappers.getMapper(IDepartmentMapper.class);

    Department toModel(DepartmentRequestDTO DepartmentRequestDTO);

    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(target = "managerName",  expression = "java(department.getManager().getUser().getName() + \" \" + department.getManager().getUser().getLastName())")
    @Mapping(source = "department.manager.user.email", target = "email")
    DepartmentResponseDTO toDTO(Department department);

    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(target = "managerName",  expression = "java(department.getManager().getUser().getName() + \" \" + department.getManager().getUser().getLastName())")
    @Mapping(source = "department.manager.user.email", target = "email")
    List<DepartmentResponseDTO> toDTO(List<Department> departments);
	
}
