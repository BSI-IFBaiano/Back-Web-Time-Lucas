package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.dto.course.CourseRequestDTO;
import com.web.desenvolvimento.edusphere.dto.course.CourseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ICourseMapper {
    ICourseMapper INSTANCE = Mappers.getMapper(ICourseMapper.class);

    Course toModel(CourseRequestDTO courseRequestDTO);

    @Mapping(source = "course.department.name", target = "departmentName")
    CourseResponseDTO toDTO(Course course);

    @Mapping(source = "course.department.name", target = "departmentName")
    List<CourseResponseDTO> toDTO(List<Course> courses);
}
