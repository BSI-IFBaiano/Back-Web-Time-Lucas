package com.web.desenvolvimento.edusphere.controllers;

import com.web.desenvolvimento.edusphere.domain.user.Role;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.dto.LoginRequest;
import com.web.desenvolvimento.edusphere.dto.LoginResponse;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.mappers.IUserMapper;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import com.web.desenvolvimento.edusphere.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
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
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody UserRequestDTO registerRequest) {
        Role role = Role.valueOf(registerRequest.role().toUpperCase());
        System.out.println(role);
        Optional<User> user = userRepository.findByUsername(registerRequest.username());

        if(user.isEmpty()) {
            User newUser = userMapper.toModel(registerRequest);
            newUser.setRole(role);
            newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
            this.userRepository.save(newUser);
            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponse(token, newUser.getUsername()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
