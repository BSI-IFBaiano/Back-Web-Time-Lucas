package com.web.desenvolvimento.edusphere.services.department;

import com.web.desenvolvimento.edusphere.domain.department.Department;
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
        boolean managerExists = managerService.managerExists(departmentRequestDTO.idManager()).getBody();

        if (!managerExists) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Gestor não existe");
        }
        Department departmentToSave = departmentMapper.toModel(departmentRequestDTO);
        departmentRepository.save(departmentToSave);
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentToSave);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentResponseDTO);
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> update(Long idDepartment, DepartmentRequestDTO departmentRequestDTO){

        Department savedDepartment = departmentRepository.findById(idDepartment).orElseThrow(
                () -> new IllegalArgumentException("Departamento não encontrado!")
        );
        Department requestDepartment = departmentMapper.toModel(departmentRequestDTO);
        requestDepartment.setIdDepartment(idDepartment);

        if(savedDepartment.getName().equals(requestDepartment.getName()) &&
                savedDepartment.getCreatedAt().equals(requestDepartment.getCreatedAt())) {

            savedDepartment.setName(requestDepartment.getName());
            savedDepartment.setManager(requestDepartment.getManager());
            departmentRepository.save(savedDepartment);

            DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(savedDepartment);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(departmentResponseDTO);
        }
        throw new IllegalArgumentException("Atualização invalida");
    }

    @Transactional
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        List<Department> deparmentList = departmentRepository.findAll();
        if (!deparmentList.isEmpty()) {
            List<DepartmentResponseDTO> departmentDTOList = departmentMapper.toDTO(deparmentList);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(departmentDTOList);
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum usuário");
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> findById(Long id) {
        Department departmentModel = departmentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Departamento não encontrado")
        );
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentModel);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(departmentResponseDTO);
    }
}
