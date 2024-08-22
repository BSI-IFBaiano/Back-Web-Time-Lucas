package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtResponseDTO;
import com.web.desenvolvimento.edusphere.services.subjectTaught.SubjectTaughtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/subjectstaught")
public class SubjectTaughtController {

    private final SubjectTaughtService subjectTaughtService;

    @Autowired
    public SubjectTaughtController(SubjectTaughtService subjectTaughtService) {
        this.subjectTaughtService = subjectTaughtService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<SubjectTaughtResponseDTO> create(
            @RequestBody @Valid SubjectTaughtRequestDTO subjectTaughtRequestDTO
    ){
        return subjectTaughtService.create(subjectTaughtRequestDTO);
    }
}
