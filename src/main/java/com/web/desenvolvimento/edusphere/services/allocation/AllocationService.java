package com.web.desenvolvimento.edusphere.services.allocation;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;
import com.web.desenvolvimento.edusphere.domain.allocation.exceptions.SubjectTaughtNotFoundException;
import com.web.desenvolvimento.edusphere.domain.subjectTaught.SubjectTaught;
import com.web.desenvolvimento.edusphere.dto.allocation.AllocationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.allocation.AllocationResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IAllocationMapper;
import com.web.desenvolvimento.edusphere.repositories.IAllocationRepository;
import com.web.desenvolvimento.edusphere.services.subjectTaught.SubjectTaughtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AllocationService {
    private final IAllocationRepository allocationRepository;
    private final IAllocationMapper allocationMapper = IAllocationMapper.INSTANCE;
    private final SubjectTaughtService subjectTaughtService;

    @Autowired
    public AllocationService(
            IAllocationRepository allocationRepository,
            SubjectTaughtService subjectTaughtService) {
        this.allocationRepository = allocationRepository;
        this.subjectTaughtService = subjectTaughtService;
    }

    public ResponseEntity<AllocationResponseDTO> create(
            AllocationRequestDTO allocationRequestDTO
    ){
        SubjectTaught subjectTaughtInternal = subjectTaughtService.findByIdInternal(
                allocationRequestDTO.idSubjectTaught()
        );
        if (subjectTaughtInternal != null) {
            Allocation allocationToSave = allocationMapper.toModel(allocationRequestDTO);
            allocationToSave.setSubjectTaught(subjectTaughtInternal);

            System.out.println("AQUI: " + allocationToSave.getYearAllocation());
            allocationRepository.save(allocationToSave);
            AllocationResponseDTO allocationResponseDTO = allocationMapper.toDTO(allocationToSave);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(allocationResponseDTO);
        }
        throw new SubjectTaughtNotFoundException("Código de disciplina lecionada não encontrado");
    }
}