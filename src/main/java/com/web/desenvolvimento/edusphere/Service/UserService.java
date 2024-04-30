package com.web.desenvolvimento.edusphere.Service;

import com.web.desenvolvimento.edusphere.DTO.UserDTO;
import com.web.desenvolvimento.edusphere.Mapper.IUserMapper;
import com.web.desenvolvimento.edusphere.Model.User;
import com.web.desenvolvimento.edusphere.Repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    private final IUserMapper userMapper = IUserMapper.INSTANCE;

    @Transactional
    public ResponseEntity<User> create(UserDTO userDTO) {
        User userToSave = userMapper.toModel(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userRepository.save(userToSave));
    }

    @Transactional
    public ResponseEntity<User> update(UserDTO userDTO) throws Exception {
        User savedUser = userRepository.findById(userDTO.idUser()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")
        );

        if(!savedUser.getPassword().equals(userDTO.password())) {
            savedUser.setPassword(userDTO.password());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userRepository.save(savedUser));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(savedUser);
    }

    @Transactional
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> userList = userRepository.findAll();
        if (!userList.isEmpty()) {
            List<UserDTO> userDTOList = userMapper.toDTO(userList);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(userDTOList);
        }
        return null;
    }

    @Transactional
    public ResponseEntity<UserDTO> findById(Long id) {
        User userModel = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!")
        );
        System.out.println(userModel.getIdUser());
        UserDTO userDTO = userMapper.toDTO(userModel);

        System.out.println(userDTO.idUser());
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userDTO);
    }

    @Transactional
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
