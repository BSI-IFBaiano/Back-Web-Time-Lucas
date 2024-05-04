package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.dto.user.UserResponseDTO;
import com.web.desenvolvimento.edusphere.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") Long idUser) {
        return userService.findById(idUser);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") Long idUser,
                                       @RequestBody @Valid UserRequestDTO userRequestDTO){
        return userService.update(idUser, userRequestDTO);
    }
}
