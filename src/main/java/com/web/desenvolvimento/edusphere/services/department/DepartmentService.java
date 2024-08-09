package com.web.desenvolvimento.edusphere.services.department;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserInvalidUpdateException;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.dto.user.UserResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IDepartmentMapper;
import com.web.desenvolvimento.edusphere.repositories.IDepartmentRepository;

@Service
public class DepartmentService {
	
	private IDepartmentRepository departmentRepository;

    private final IDepartmentMapper departmentMapper = IDepartmentMapper.INSTANCE;

    public DepartmentService(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> create(DepartmentRequestDTO departmentRequestDTO) {
        Department departmentToSave = departmentMapper.toModel(departmentRequestDTO);
        departmentRepository.save(departmentToSave);
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentToSave);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentResponseDTO);
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> update(Long idDepartment, DepartmentRequestDTO departmentRequestDTO){

        Department savedDepartment = departmentRepository.findById(idDepartment).orElseThrow(
                () -> new UserNotFoundException("Departamento não encontrado!")
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
        throw new UserInvalidUpdateException("Atualização invalida");
    }

    @Transactional
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        List<Department> deparmentList = departmentRepository.findAll();
        if (!deparmentList.isEmpty()) {
            List<DepartmentResponseDTO> departmentDTOList = departmentMapper.toDTO(deparmentList);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(departmentDTOList);
        }
        throw new UserNotFoundException("Nenhum departamento encontrado");
    }

    @Transactional
    public ResponseEntity<DepartmentResponseDTO> findById(Long id) {
        Department departmentModel = departmentRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Departamento não encontrado")
        );
        DepartmentResponseDTO departmentResponseDTO = departmentMapper.toDTO(departmentModel);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(departmentResponseDTO);
    }
    
}
