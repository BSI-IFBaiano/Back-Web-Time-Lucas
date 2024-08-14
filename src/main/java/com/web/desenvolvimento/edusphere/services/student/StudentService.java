package com.web.desenvolvimento.edusphere.services.student;

import com.web.desenvolvimento.edusphere.domain.course.Course;
import com.web.desenvolvimento.edusphere.domain.course.exceptions.CourseOrUserNotFoundException;
import com.web.desenvolvimento.edusphere.domain.student.Student;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotHaveThisRoleException;
import com.web.desenvolvimento.edusphere.dto.student.StudentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.student.StudentResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IStudentMapper;
import com.web.desenvolvimento.edusphere.repositories.IStudentRepository;
import com.web.desenvolvimento.edusphere.services.course.CourseService;
import com.web.desenvolvimento.edusphere.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final IStudentRepository studentRepository;
    private final IStudentMapper studentMapper = IStudentMapper.INSTANCE;
    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public StudentService(IStudentRepository studentRepository, UserService userService,
                          CourseService courseService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.courseService = courseService;
    }

    @Transactional
    public ResponseEntity<StudentResponseDTO> addStudent(StudentRequestDTO studentRequestDTO) {
        User userInternal = userService.findByIdInternal(studentRequestDTO.idUser());
        Course studentCourse = courseService.findByIdInternal(studentRequestDTO.idCourse());

        if (userInternal != null && studentCourse != null) {
            if (!"STUDENT".equals(userInternal.getRole().name())) {
                throw new UserNotHaveThisRoleException("O usuário não é um aluno(a)!");
            }
            Student studentToSave = studentMapper.toModel(studentRequestDTO);

            studentToSave.setUser(userInternal);
            studentToSave.setCourse(studentCourse);

            studentRepository.save(studentToSave);

            StudentResponseDTO studentResponseDTO = studentMapper.toDTO(studentToSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDTO);
        }
        throw new CourseOrUserNotFoundException("Usuário ou curso não foram encontrados");
    }
}
