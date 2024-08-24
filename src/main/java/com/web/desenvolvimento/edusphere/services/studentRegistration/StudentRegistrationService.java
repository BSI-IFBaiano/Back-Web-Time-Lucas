package com.web.desenvolvimento.edusphere.services.studentRegistration;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;
import com.web.desenvolvimento.edusphere.domain.student.Student;
import com.web.desenvolvimento.edusphere.domain.studentRegistration.StudentRegistration;
import com.web.desenvolvimento.edusphere.domain.studentRegistration.exceptions.StudentOrAllocationNotFoundException;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IStudentRegistrarionMapper;
import com.web.desenvolvimento.edusphere.repositories.IStudentRegistrationRepository;
import com.web.desenvolvimento.edusphere.services.allocation.AllocationService;
import com.web.desenvolvimento.edusphere.services.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentRegistrationService {
    private final IStudentRegistrarionMapper studentRegistrarionMapper = IStudentRegistrarionMapper.INSTANCE;
    private final IStudentRegistrationRepository studentRegistrationRepository;
    private final StudentService studentService;
    private final AllocationService allocationService;

    @Autowired
    public StudentRegistrationService(
            IStudentRegistrationRepository studentRegistrationRepository,
            StudentService studentService, AllocationService allocationService) {
        this.studentRegistrationRepository = studentRegistrationRepository;
        this.studentService = studentService;
        this.allocationService = allocationService;
    }

    @Transactional
    public ResponseEntity<StudentRegistrationResponseDTO> create(
            StudentRegistrationRequestDTO studentRegistrationRequestDTO) {

        Student studentInternal = studentService.findByIdInternal(studentRegistrationRequestDTO.idStudent());
        Allocation allocationInternal = allocationService.findByIdInternal(studentRegistrationRequestDTO.idAllocation());

        if (studentInternal == null && allocationInternal == null) {
            throw new StudentOrAllocationNotFoundException("Código de estudante e alocação não encontrados");
        }
        StudentRegistration studentRegistrationToSave = studentRegistrarionMapper.toModel(studentRegistrationRequestDTO);
        studentRegistrationToSave.setStudent(studentInternal);
        studentRegistrationToSave.setAllocation(allocationInternal);

        studentRegistrationRepository.save(studentRegistrationToSave);

        StudentRegistrationResponseDTO studentRegistrationResponseDTO = studentRegistrarionMapper.toDTO(studentRegistrationToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRegistrationResponseDTO);
    }

}
