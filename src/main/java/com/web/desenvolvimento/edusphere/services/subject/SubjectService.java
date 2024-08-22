package com.web.desenvolvimento.edusphere.services.subject;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.department.exceptions.DepartmentNotFoundException;
import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ISubjectMapper;
import com.web.desenvolvimento.edusphere.repositories.ISubjectRepository;
import com.web.desenvolvimento.edusphere.services.department.DepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    private final ISubjectRepository subjectRepository;
    private final ISubjectMapper subjectMapper = ISubjectMapper.INSTANCE;
    private final DepartmentService departmentService;

    @Autowired
    public SubjectService(ISubjectRepository subjectRepository, DepartmentService departmentService) {
        this.subjectRepository = subjectRepository;
        this.departmentService = departmentService;
    }

    @Transactional
    public ResponseEntity<SubjectResponseDTO> addSubject(SubjectRequestDTO subjectRequestDTO) {
        Department subjectDepartment = departmentService.findByIdInternal(
                subjectRequestDTO.idDepartment()
        );
        if (subjectDepartment != null) {
            Subject subjectToSave = subjectMapper.toModel(subjectRequestDTO);
            subjectToSave.setDepartment(subjectDepartment);

            subjectRepository.save(subjectToSave);

            SubjectResponseDTO subjectResponseDTO = subjectMapper.toDTO(subjectToSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectResponseDTO);
        }
        throw new DepartmentNotFoundException("Departamento não foi encontrado!");
    }

    @Transactional
    public Subject findByIdInternal(Long id) {
        return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Disciplina não encontrado"));
    }
}
