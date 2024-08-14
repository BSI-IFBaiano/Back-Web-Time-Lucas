package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.student.StudentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.student.StudentResponseDTO;
import com.web.desenvolvimento.edusphere.services.student.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponseDTO> create(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return studentService.addStudent(studentRequestDTO);
    }
}
