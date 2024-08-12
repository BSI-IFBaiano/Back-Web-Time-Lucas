package com.web.desenvolvimento.edusphere.services.department;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IDepartmentMapper;
import com.web.desenvolvimento.edusphere.repositories.IDepartmentRepository;
import com.web.desenvolvimento.edusphere.services.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        if (managerInternal == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gestor não existe");
        }
        Department departmentToSave = departmentMapper.toModel(departmentRequestDTO);
        departmentToSave.setManager(managerInternal);
        departmentRepository.save(departmentToSave);
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentToSave);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentResponseDTO);
    }

}
