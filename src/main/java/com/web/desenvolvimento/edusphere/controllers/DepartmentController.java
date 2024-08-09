package com.web.desenvolvimento.edusphere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.desenvolvimento.edusphere.dto.department.DepartmentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.department.DepartmentResponseDTO;
import com.web.desenvolvimento.edusphere.services.department.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/edusphere/departments")
public class DepartmentController {

	@Autowired
    private DepartmentService departmentService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DepartmentResponseDTO>> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DepartmentResponseDTO> findById(@PathVariable("id") Long idDepartment) {
        return departmentService.findById(idDepartment);
    }


    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody @Valid DepartmentRequestDTO departmentRequestDTO) {
        return departmentService.create(departmentRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DepartmentResponseDTO> update(@PathVariable("id") Long idDepartment,
                                       @RequestBody @Valid DepartmentRequestDTO departmentRequestDTO){
        return departmentService.update(idDepartment, departmentRequestDTO);
    }
    
}
