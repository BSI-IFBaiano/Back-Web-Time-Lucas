package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectInCourse.SubjectInCourseResponseDTO;
import com.web.desenvolvimento.edusphere.services.subjectInCourse.SubjectInCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/subjectsincourse")
public class SubjectInCourseController {
    private final SubjectInCourseService subjectInCourseService;

    @Autowired
    public SubjectInCourseController(SubjectInCourseService subjectInCourseService) {
        this.subjectInCourseService = subjectInCourseService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<SubjectInCourseResponseDTO> create(
            @RequestBody @Valid SubjectInCourseRequestDTO subjectInCourseRequestDTO
    ) {
        return subjectInCourseService.create(subjectInCourseRequestDTO);
    }
}
