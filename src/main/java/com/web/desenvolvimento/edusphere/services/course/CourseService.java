package com.web.desenvolvimento.edusphere.services.course;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.domain.course.exceptions.CourseNotFoundException;
import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.department.exceptions.DepartmentNotFoundException;
import com.web.desenvolvimento.edusphere.dto.course.CourseRequestDTO;
import com.web.desenvolvimento.edusphere.dto.course.CourseResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ICourseMapper;
import com.web.desenvolvimento.edusphere.repositories.ICourseRepository;
import com.web.desenvolvimento.edusphere.services.department.DepartmentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    private final ICourseRepository courseRepository;
    private final ICourseMapper courseMapper = ICourseMapper.INSTANCE;
    private final DepartmentService departmentService;

    @Autowired
    public CourseService(ICourseRepository courseRepository, DepartmentService departmentService) {
        this.courseRepository = courseRepository;
        this.departmentService = departmentService;
    }

    @Transactional
    public ResponseEntity<CourseResponseDTO> create(CourseRequestDTO courseRequestDTO) {
        Department departmentInternal = departmentService.findByIdInternal(
                courseRequestDTO.idDepartment());
        if (departmentInternal == null) {
            throw new DepartmentNotFoundException("Departamento não encontrado!");
        }
        Course courseToSave = courseMapper.toModel(courseRequestDTO);
        courseToSave.setDepartment(departmentInternal);
        courseToSave = courseRepository.save(courseToSave);

        CourseResponseDTO courseResponseDTO = courseMapper.toDTO(courseToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponseDTO);
    }

    @Transactional
    public ResponseEntity<List<CourseResponseDTO>> findAll() {
        var listCourseResponse = courseMapper.toDTO(courseRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(listCourseResponse);
    }

    @Transactional
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            courseRepository.delete(course.get());  
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
    }

    @Transactional
    public Course findByIdInternal(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Curso não encontrado!"));
    }
}
