package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.subjectInCourse.SubjectInCourse;
import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ISubjectInCourseMapper {
    ISubjectInCourseMapper INSTANCE = Mappers.getMapper(ISubjectInCourseMapper.class);

    SubjectInCourse toModel(SubjectInCourseRequestDTO subjectInCourseRequestDTO);

    @Mapping(target = "courseName", source = "course.name")
    @Mapping(target = "subjectName", source = "subject.name")
    SubjectInCourseResponseDTO toDTO(SubjectInCourse subjectInCourse);

}
