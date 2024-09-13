package com.web.desenvolvimento.edusphere.services.department;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.domain.manager.expections.ManagerNotFoundException;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IDepartmentMapper;
import com.web.desenvolvimento.edusphere.repositories.IDepartmentRepository;
import com.web.desenvolvimento.edusphere.services.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final IDepartmentRepository departmentRepository;
    private final ManagerService managerService;
    private final IDepartmentMapper departmentMapper = IDepartmentMapper.INSTANCE;

    @Autowired
    public DepartmentService(IDepartmentRepository departmentRepository, ManagerService managerService) {
        this.departmentRepository = departmentRepository;
        this.managerService = managerService;
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> create(DepartmentRequestDTO departmentRequestDTO) {
        Manager managerInternal = managerService.findByIdInternal(departmentRequestDTO.idManager());

        if (managerInternal != null) {
            Department departmentToSave = departmentMapper.toModel(departmentRequestDTO);
            departmentToSave.setManager(managerInternal);
            departmentRepository.save(departmentToSave);
            DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(departmentResponseDTO);
        }
        throw new ManagerNotFoundException("Gestor não existe!");
    }

    @Transactional
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        var listDepartmentResponse = departmentMapper.toDTO(departmentRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(listDepartmentResponse);
    }

    @Transactional
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            departmentRepository.delete(department.get());  
    

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).build();
        }
    }

    @Transactional
    public Department findByIdInternal(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Departamento não encontrado"));
    }
}
