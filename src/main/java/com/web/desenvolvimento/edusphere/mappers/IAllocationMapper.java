package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;
import com.web.desenvolvimento.edusphere.dto.allocation.AllocationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.allocation.AllocationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAllocationMapper {
    IAllocationMapper INSTANCE = Mappers.getMapper(IAllocationMapper.class);

    @Mapping(source = "yearAllocation", target = "yearAllocation")
    Allocation toModel(AllocationRequestDTO allocationRequestDTO);

    @Mapping(source = "allocation.subjectTaught.subject.name", target = "subjectName")
    @Mapping(target = "teacherName",  expression = "java(allocation.getSubjectTaught().getTeacher().getUser().getName() + \" \" + allocation.getSubjectTaught().getTeacher().getUser().getLastName())")
    AllocationResponseDTO toDTO(Allocation allocation);
}
