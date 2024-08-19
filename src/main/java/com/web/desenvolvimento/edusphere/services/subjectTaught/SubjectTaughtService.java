package com.web.desenvolvimento.edusphere.services.subjectTaught;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.desenvolvimento.edusphere.domain.department.Department;
import com.web.desenvolvimento.edusphere.domain.department.exceptions.DepartmentNotFoundException;
import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import com.web.desenvolvimento.edusphere.dto.subject.SubjectResponseDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtRequestDTO;
import com.web.desenvolvimento.edusphere.dto.subjectTaught.SubjectTaughtResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.ISubjectTaughtMapper;
import com.web.desenvolvimento.edusphere.repositories.ISubjectTaughtRepository;

import jakarta.transaction.Transactional;

@Service
public class SubjectTaughtService {
	private final ISubjectTaughtRepository iSubjectTaughtRepository;
	private final ISubjectTaughtMapper subjectTaughtMapper = ISubjectTaughtMapper.INSTANCE;

	@Autowired
	public SubjectTaughtService(ISubjectTaughtRepository iSubjectTaughtRepository) {
		this.iSubjectTaughtRepository = iSubjectTaughtRepository;
	}

	@Transactional
	public ResponseEntity<SubjectTaughtResponseDTO> addSubjectTaught(SubjectTaughtRequestDTO subjectTaughtRequestDTO) {
		SubjectTaught subjectTaughtToSave = subjectTaughtMapper.toModel(subjectTaughtRequestDTO);

		iSubjectTaughtRepository.save(subjectTaughtToSave);

		SubjectTaughtResponseDTO subjectTaughtResponseDTO = subjectTaughtMapper.toDTO(subjectTaughtToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(subjectTaughtResponseDTO);
	}

}
