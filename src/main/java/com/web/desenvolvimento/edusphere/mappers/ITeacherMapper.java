package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherRequestDTO;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITeacherMapper {
    ITeacherMapper INSTANCE = Mappers.getMapper(ITeacherMapper.class);

    Teacher toModel(TeacherRequestDTO teacherRequestDTO);

    @Mapping(source = "teacher.department.name", target = "departmentName")
    @Mapping(target = "teacherName",  expression = "java(teacher.getUser().getName() + \" \" + teacher.getUser().getLastName())")
    @Mapping(source = "teacher.user.email", target = "email")
    TeacherResponseDTO toDTO(Teacher teacher);
}
