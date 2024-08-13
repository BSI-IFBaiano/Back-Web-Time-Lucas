package com.web.desenvolvimento.edusphere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.desenvolvimento.edusphere.dto.manager.ManagerRequestDTO;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerResponseDTO;
import com.web.desenvolvimento.edusphere.services.manager.ManagerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/edusphere/managers")
public class ManagerController {

    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ManagerResponseDTO>> findAll() {
        return managerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ManagerResponseDTO> findById(@PathVariable("id") Long idManager) {
        return managerService.findById(idManager);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ManagerResponseDTO> create(@RequestBody @Valid ManagerRequestDTO managerRequestDTO) {
        return managerService.create(managerRequestDTO);
    }

}
