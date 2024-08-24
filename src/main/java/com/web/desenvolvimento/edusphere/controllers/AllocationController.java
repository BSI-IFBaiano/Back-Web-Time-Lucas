package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.dto.allocation.AllocationRequestDTO;
import com.web.desenvolvimento.edusphere.dto.allocation.AllocationResponseDTO;
import com.web.desenvolvimento.edusphere.services.allocation.AllocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/edusphere/allocations")
public class AllocationController {
    private final AllocationService allocationService;

    @Autowired
    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<AllocationResponseDTO> create(
            @RequestBody @Valid AllocationRequestDTO allocationRequestDTO
    ) {
        System.out.println(allocationRequestDTO.yearAllocation());
        return allocationService.create(allocationRequestDTO);
    }
}
