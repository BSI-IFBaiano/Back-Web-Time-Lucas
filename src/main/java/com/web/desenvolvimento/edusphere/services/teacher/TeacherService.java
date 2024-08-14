package com.web.desenvolvimento.edusphere.services.teacher;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;
import com.web.desenvolvimento.edusphere.domain.teacher.exceptions.DepartmentOrUserNotFoundException;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotHaveThisRoleException;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherRequestDTO;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ITeacherMapper;
import com.web.desenvolvimento.edusphere.repositories.ITeacherRepository;
import com.web.desenvolvimento.edusphere.services.department.DepartmentService;
import com.web.desenvolvimento.edusphere.services.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    private final ITeacherRepository teacherRepository;
    private final ITeacherMapper teacherMapper = ITeacherMapper.INSTANCE;
    private final UserService userService;
    private final DepartmentService departmentService;

    @Autowired
    public TeacherService(ITeacherRepository teacherRepository, UserService userService,
                          DepartmentService departmentService) {
        this.teacherRepository = teacherRepository;
        this.userService = userService;
        this.departmentService = departmentService;
    }

    @Transactional
    public ResponseEntity<TeacherResponseDTO> create(TeacherRequestDTO teacherRequestDTO) {
        User userInternal = userService.findByIdInternal(teacherRequestDTO.idUser());
        Department departmentInternal = departmentService.findByIdInternal(
                teacherRequestDTO.idDepartment()
        );

        if (userInternal != null && departmentInternal != null) {
            if (!"TEACHER".equals(userInternal.getRole().name())) {
                throw new UserNotHaveThisRoleException("O usuário não é um professor(a)!");
            }
            Teacher teacherToSave = teacherMapper.toModel(teacherRequestDTO);
            teacherToSave.setDepartment(departmentInternal);
            teacherToSave.setUser(userInternal);

            teacherRepository.save(teacherToSave);
            TeacherResponseDTO teacherResponseDTO = teacherMapper.toDTO(teacherToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(teacherResponseDTO);
        }
        throw new DepartmentOrUserNotFoundException("Código de usuário ou departamento não encontrados!");
    }
}
