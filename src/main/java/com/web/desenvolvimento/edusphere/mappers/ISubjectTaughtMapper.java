package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISubjectTaughtMapper
{
    ISubjectTaughtMapper INSTANCE = Mappers.getMapper(ISubjectTaughtMapper.class);

    SubjectTaught toModel(SubjectTaughtRequestDTO subjectTaughtRequestDTO);

    @Mapping(target = "subjectName", source = "subject.name")
    @Mapping(target = "teacherName", expression = "java(subjectTaught.getTeacher().getUser().getName() + \" \" + subjectTaught.getTeacher().getUser().getLastName())")
    SubjectTaughtResponseDTO toDTO(SubjectTaught subjectTaught);
}
