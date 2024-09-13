package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;
import com.web.desenvolvimento.edusphere.services.department.DepartmentService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/edusphere/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody @Valid DepartmentRequestDTO departmentRequestDTO) {
        return departmentService.create(departmentRequestDTO);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        return departmentService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return departmentService.deleteById(id);
    }

}
