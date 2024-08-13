package com.web.desenvolvimento.edusphere.services.user;

import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserInvalidUpdateException;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.dto.user.UserResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IUserMapper;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final IUserMapper userMapper = IUserMapper.INSTANCE;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<UserResponseDTO> create(UserRequestDTO userRequestDTO) {
        User userToSave = userMapper.toModel(userRequestDTO);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userRepository.save(userToSave);
        UserResponseDTO userResponseDTO = userMapper.toDTO(userToSave);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userResponseDTO);
    }

    @Transactional
    public ResponseEntity<UserResponseDTO> update(Long idUser, UserRequestDTO userRequestDTO){

        User savedUser = userRepository.findById(idUser).orElseThrow(
                () -> new UserNotFoundException("Usuário não encontrado!")
        );
        User requestUser = userMapper.toModel(userRequestDTO);
        requestUser.setIdUser(idUser);

        if(savedUser.getUsername().equals(requestUser.getUsername()) &&
            savedUser.getEmail().equals(requestUser.getEmail()) &&
            savedUser.getCreatedAt().equals(requestUser.getCreatedAt())) {

            savedUser.setPassword(requestUser.getPassword());
            savedUser.setPhone(requestUser.getPhone());
            userRepository.save(savedUser);

            UserResponseDTO userResponseDTO = userMapper.toDTO(savedUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userResponseDTO);
        }
        throw new UserInvalidUpdateException("Atualização invalida");
    }

    @Transactional
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> userList = userRepository.findAll();
        if (!userList.isEmpty()) {
            List<UserResponseDTO> userDTOList = userMapper.toDTO(userList);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(userDTOList);
        }
        throw new UserNotFoundException("Nenhum usuário encontrado");
    }

    @Transactional
    public ResponseEntity<UserResponseDTO> findById(Long id) {
        User userModel = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Usuário não encontrado")
        );
        UserResponseDTO userResponseDTO = userMapper.toDTO(userModel);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userResponseDTO);
    }

    @Transactional
    public User findByIdInternal(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @Transactional
    public ResponseEntity<Boolean> userExists(Long id) {
        return ResponseEntity.ok(userRepository.existsById(id));
    }
}
