package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.studentRegistration.StudentRegistration;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentRegistrarionMapper {
    IStudentRegistrarionMapper INSTANCE = Mappers.getMapper(IStudentRegistrarionMapper.class);

    StudentRegistration toModel(StudentRegistrationRequestDTO studentRegistrationRequestDTO);

    @Mapping(target = "idStudent", source = "studentRegistration.student.idStudent")
    @Mapping(target = "idAllocation", source = "studentRegistration.allocation.idAllocation")
    @Mapping(target = "studentName",  expression = "java(studentRegistration.getStudent().getUser().getName() + \" \" + studentRegistration.getStudent().getUser().getLastName())")
    @Mapping(target = "teacherName",  expression = "java(studentRegistration.getAllocation().getSubjectTaught().getTeacher().getUser().getName() + \" \" + studentRegistration.getAllocation().getSubjectTaught().getTeacher().getUser().getLastName())")
    @Mapping(target = "semester", source = "studentRegistration.allocation.semester")
    @Mapping(target = "yearAllocation", source = "studentRegistration.allocation.yearAllocation")
    StudentRegistrationResponseDTO toDTO(StudentRegistration studentRegistration);
}
