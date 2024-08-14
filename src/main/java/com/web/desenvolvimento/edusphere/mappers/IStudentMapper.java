package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.student.Student;
import com.web.desenvolvimento.edusphere.dto.student.StudentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.student.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {
    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);

    Student toModel(StudentRequestDTO studentRequestDTO);

    @Mapping(target = "studentName",  expression = "java(student.getUser().getName() + \" \" + student.getUser().getLastName())")
    @Mapping(source="student.user.email", target = "email")
    @Mapping(source = "student.course.name", target = "courseName")
    StudentResponseDTO toDTO(Student student);
}
