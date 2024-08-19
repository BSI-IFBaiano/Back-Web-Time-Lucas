package com.web.desenvolvimento.edusphere.mappers;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtResponseDTO;

public interface ISubjectTaughtMapper {
	ISubjectTaughtMapper INSTANCE = Mappers.getMapper(ISubjectTaughtMapper.class);

    SubjectTaught toModel(SubjectTaughtRequestDTO subjectTaughtRequestDTO);

    @Mapping(source = "subjectTaught.department.name", target = "departmentName")
    @Mapping(source = "subjectTaught.subject.name", target = "subjectName")
    SubjectTaughtResponseDTO toDTO(SubjectTaught subjectTaught);
}
