package com.web.desenvolvimento.edusphere.services.subjectTaught;

import com.web.desenvolvimento.edusphere.domain.subject.Subject;
import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import com.web.desenvolvimento.edusphere.domain.subjectTaught.exceptions.TeacherOrSubjectNotFoundException;
import com.web.desenvolvimento.edusphere.domain.teacher.Teacher;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ISubjectTaughtMapper;
import com.web.desenvolvimento.edusphere.repositories.ISubjectTaughtRepository;
import com.web.desenvolvimento.edusphere.services.subject.SubjectService;
import com.web.desenvolvimento.edusphere.services.teacher.TeacherService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SubjectTaughtService {
    private final ISubjectTaughtRepository subjectTaughtRepository;
    private final ISubjectTaughtMapper subjectTaughtMapper = ISubjectTaughtMapper.INSTANCE;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @Autowired
    public SubjectTaughtService(
            ISubjectTaughtRepository subjectTaughtRepository,
            TeacherService teacherService, SubjectService subjectService) {
        this.subjectTaughtRepository = subjectTaughtRepository;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
    }

    @Transactional
    public ResponseEntity<SubjectTaughtResponseDTO> create(
            SubjectTaughtRequestDTO subjectTaughtRequestDTO
    ) {
        Teacher teacherInternal = teacherService.findByIdInternal(subjectTaughtRequestDTO.idTeacher());
        Subject subjectInternal = subjectService.findByIdInternal(subjectTaughtRequestDTO.idSubject());

        if (teacherInternal != null && subjectInternal != null) {
            SubjectTaught subjectTaughtToSave = subjectTaughtMapper.toModel(subjectTaughtRequestDTO);
            subjectTaughtToSave.setTeacher(teacherInternal);
            subjectTaughtToSave.setSubject(subjectInternal);

            subjectTaughtRepository.save(subjectTaughtToSave);

            SubjectTaughtResponseDTO subjectTaughtResponseDTO = subjectTaughtMapper.toDTO(subjectTaughtToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(subjectTaughtResponseDTO);
        }
        throw new TeacherOrSubjectNotFoundException("Professor ou disciplina não encontrados!");
    }

    @Transactional
    public SubjectTaught findByIdInternal(Long id) {
        return subjectTaughtRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Código de disciplina lecionada não encontrado"));
    }
}
