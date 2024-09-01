package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.domain.user.Role;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.dto.login.LoginRequest;
import com.web.desenvolvimento.edusphere.dto.login.LoginResponse;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.mappers.IUserMapper;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import com.web.desenvolvimento.edusphere.infra.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/edusphere")
public class AuthenticationController {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final IUserMapper userMapper = IUserMapper.INSTANCE;

    public AuthenticationController(IUserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username()).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

        if(passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getRole().toString()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequestDTO registerRequest) {
        Role role = Role.valueOf(registerRequest.role().toUpperCase());
        System.out.println(role);
        Optional<User> user = userRepository.findByUsername(registerRequest.username());

        if(user.isEmpty()) {
            User newUser = userMapper.toModel(registerRequest);
            newUser.setRole(role);
            newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
            this.userRepository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successo! Usuário criado!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro! Impossível criar usuário!");
        }
    }
}
