package com.web.desenvolvimento.edusphere.dto.subjectTaught;

import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;

import jakarta.validation.constraints.NotBlank;

public record SubjectTaughtResponseDTO(Long idSubjectTaught, @NotBlank Subject subject, @NotBlank Teacher teacher) {

}
