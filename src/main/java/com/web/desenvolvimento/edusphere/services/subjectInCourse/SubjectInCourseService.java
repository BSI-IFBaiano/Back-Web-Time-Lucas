package com.web.desenvolvimento.edusphere.services.subjectInCourse;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.domain.subjectInCourse.SubjectInCourse;
import com.web.desenvolvimento.edusphere.domain.subjectInCourse.exceptions.CourseOrSubjectNotFoundException;
import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ISubjectInCourseMapper;
import com.web.desenvolvimento.edusphere.repositories.ISubjectInCourseRepository;
import com.web.desenvolvimento.edusphere.services.course.CourseService;
import com.web.desenvolvimento.edusphere.services.subject.SubjectService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectInCourseService {

    private final ISubjectInCourseRepository subjectInCourseRepository;
    private final CourseService courseService;
    private final SubjectService subjectService;
    private final ISubjectInCourseMapper subjectInCourseMapper = ISubjectInCourseMapper.INSTANCE;

    public SubjectInCourseService(ISubjectInCourseRepository subjectInCourseRepository,
                                  CourseService courseService, SubjectService subjectService
    ) {
        this.subjectInCourseRepository = subjectInCourseRepository;
        this.courseService = courseService;
        this.subjectService = subjectService;
    }

    @Transactional
    public ResponseEntity<SubjectInCourseResponseDTO> create(
            SubjectInCourseRequestDTO subjectInCourseRequestDTO
    ) {
        Course courseInternal = courseService.findByIdInternal(subjectInCourseRequestDTO.idCourse());
        Subject subjectInternal = subjectService.findByIdInternal(subjectInCourseRequestDTO.idSubject());

        if (courseInternal != null && subjectInternal != null) {
            SubjectInCourse subjectInCourseToSave = subjectInCourseMapper.toModel(subjectInCourseRequestDTO);
            subjectInCourseToSave.setCourse(courseInternal);
            subjectInCourseToSave.setSubject(subjectInternal);

            subjectInCourseRepository.save(subjectInCourseToSave);

            SubjectInCourseResponseDTO subjectInCourseResponseDTO = subjectInCourseMapper.toDTO(subjectInCourseToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(subjectInCourseResponseDTO);
        }
        throw new CourseOrSubjectNotFoundException("Curso ou disciplina não encontrados!");
    }
}
