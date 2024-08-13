package com.web.desenvolvimento.edusphere.services.manager;

import java.util.List;

import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotHaveThisRoleException;
import com.web.desenvolvimento.edusphere.mappers.IUserMapper;
import com.web.desenvolvimento.edusphere.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerRequestDTO;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IManagerMapper;
import com.web.desenvolvimento.edusphere.repositories.IManagerRepository;

@Service
public class ManagerService {

	private final IManagerRepository managerRepository;
	private final UserService userService;
	private final IUserMapper userMapper = IUserMapper.INSTANCE;
	private final IManagerMapper managerMapper = IManagerMapper.INSTANCE;

	@Autowired
	public ManagerService(IManagerRepository managerRepository, UserService userService) {
		this.managerRepository = managerRepository;
		this.userService = userService;
	}

	@Transactional
	public ResponseEntity<ManagerResponseDTO> create(ManagerRequestDTO managerRequestDTO) {
		User userInternal = userService.findByIdInternal(managerRequestDTO.idUser());

		if (userInternal != null && userInternal.getRole() != null) {
			if (!"MANAGER".equals(userInternal.getRole().name()) ) {
				throw new UserNotHaveThisRoleException("O usuário não é um gestor!");
			}
			Manager managerToSave = managerMapper.toModel(managerRequestDTO);
			managerToSave.setUser(userInternal);
			managerRepository.save(managerToSave);

			ManagerResponseDTO managerResponseDTO = managerMapper.toDTO(managerToSave);
			return ResponseEntity.status(HttpStatus.CREATED).body(managerResponseDTO);
		}
		throw new UserNotFoundException("Usuário não foi encontrado ou não é um gestor!");
	}


	@Transactional
	public ResponseEntity<List<ManagerResponseDTO>> findAll() {
		List<Manager> managerList = managerRepository.findAll();
		if (!managerList.isEmpty()) {
			List<ManagerResponseDTO> managerDTOList = managerMapper.toDTO(managerList);
			return ResponseEntity.status(HttpStatus.FOUND).body(managerDTOList);
		}
		throw new UserNotFoundException("Nenhum usuário encontrado");
	}

	@Transactional
	public ResponseEntity<ManagerResponseDTO> findById(Long id) {
		Manager managerModel = managerRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		ManagerResponseDTO managerResponseDTO = managerMapper.toDTO(managerModel);
		return ResponseEntity.status(HttpStatus.FOUND).body(managerResponseDTO);
	}

	@Transactional
	public ResponseEntity<Boolean> managerExists(Long id) {
		return ResponseEntity.ok(managerRepository.existsById(id));
	}

	@Transactional
    public Manager findByIdInternal(Long id) {
		return managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Gestor não encontrado"));
	}
}
