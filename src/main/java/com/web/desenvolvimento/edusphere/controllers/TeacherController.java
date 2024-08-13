package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.teacher.TeacherRequestDTO;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherResponseDTO;
import com.web.desenvolvimento.edusphere.services.teacher.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TeacherResponseDTO> create(@RequestBody @Valid TeacherRequestDTO teacherRequestDTO) {
        return teacherService.create(teacherRequestDTO);
    }
}
