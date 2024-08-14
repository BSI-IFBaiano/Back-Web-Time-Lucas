package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.student.StudentRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectResponseDTO;
import com.web.desenvolvimento.edusphere.dto.teacher.TeacherResponseDTO;
import com.web.desenvolvimento.edusphere.services.subject.SubjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<SubjectResponseDTO> create(@RequestBody @Valid SubjectRequestDTO subjectRequestDTO) {
        return subjectService.addSubject(subjectRequestDTO);
    }
}
