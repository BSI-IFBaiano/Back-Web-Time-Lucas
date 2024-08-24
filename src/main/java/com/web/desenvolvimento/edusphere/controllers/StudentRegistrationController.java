package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.student.StudentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.student.StudentResponseDTO;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.studentRegistration.StudentRegistrationResponseDTO;
import com.web.desenvolvimento.edusphere.services.studentRegistration.StudentRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/studentRegistrationController")
public class StudentRegistrationController {
    private final StudentRegistrationService studentRegistrationService;

    @Autowired
    public StudentRegistrationController(StudentRegistrationService studentRegistrationService) {
        this.studentRegistrationService = studentRegistrationService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('STUDENT')")
    public ResponseEntity<StudentRegistrationResponseDTO> create(
            @RequestBody @Valid StudentRegistrationRequestDTO studentRegistrationRequestDTO) {
        return studentRegistrationService.create(studentRegistrationRequestDTO);
    }
}
