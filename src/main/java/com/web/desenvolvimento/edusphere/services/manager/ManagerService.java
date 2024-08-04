package com.web.desenvolvimento.edusphere.services.manager;

import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerRequest;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerResponse;
import com.web.desenvolvimento.edusphere.mappers.IManagerMapper;
import com.web.desenvolvimento.edusphere.repositories.IManagerRepository;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ManagerService {
    private IManagerRepository managerRepository;
    private IUserRepository userRepository;
    private final IManagerMapper managerMapper = IManagerMapper.INSTANCE;

    public ManagerService(IManagerRepository managerRepository, IUserRepository userRepository) {
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<ManagerResponse> createManager(ManagerRequest managerRequest) {
        User managerUser = userRepository.findById(managerRequest.idUser()).orElseThrow(
                ()-> new RuntimeException("Usuário não existe")
        );
        if (!managerUser.getRole().name().equals("MANAGER")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        var managerToSave = managerMapper.toModel(managerRequest);
        managerToSave.setUser(managerUser);
        managerRepository.save(managerToSave);
        var managerResponse = managerMapper.toDTO(managerToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(managerResponse);
    }
}
