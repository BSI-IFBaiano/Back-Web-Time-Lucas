package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISubjectMapper {
    ISubjectMapper INSTANCE = Mappers.getMapper(ISubjectMapper.class);

    Subject toModel(SubjectRequestDTO subjectRequestDTO);

    @Mapping(source = "subject.department.name", target = "departmentName")
    SubjectResponseDTO toDTO(Subject subject);
}
