package com.web.desenvolvimento.edusphere.services.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserInvalidUpdateException;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerRequestDTO;
import com.web.desenvolvimento.edusphere.dto.manager.ManagerResponseDTO;
import com.web.desenvolvimento.edusphere.mappers.IManagerMapper;
import com.web.desenvolvimento.edusphere.repositories.IManagerRepository;

public class ManagerService {

	private IManagerRepository managerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final IManagerMapper managerMapper = IManagerMapper.INSTANCE;

	public ManagerService(IManagerRepository managerRepository) {
		this.managerRepository = managerRepository;
	}

	@Transactional
	public ResponseEntity<ManagerResponseDTO> create(ManagerRequestDTO managerRequestDTO) {
		Manager managerToSave = managerMapper.toModel(managerRequestDTO);
		managerToSave.setPassword(passwordEncoder.encode(managerToSave.getUser().getPassword()));
		managerRepository.save(managerToSave);
		ManagerResponseDTO managerResponseDTO = managerMapper.toDTO(managerToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(managerResponseDTO);
	}

	@Transactional
	public ResponseEntity<ManagerResponseDTO> update(Long idManager, ManagerRequestDTO managerRequestDTO) {

		Manager savedManager = managerRepository.findById(idManager)
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
		Manager requestManager = managerMapper.toModel(managerRequestDTO);
		requestManager.setIdManager(idManager);

		if (savedManager.getUser().getUsername().equals(requestManager.getUser().getUsername())
				&& savedManager.getUser().getEmail().equals(requestManager.getUser().getEmail())
				&& savedManager.getUser().getCreatedAt().equals(requestManager.getUser().getCreatedAt())) {

			savedManager.getUser().setPassword(requestManager.getUser().getPassword());
			savedManager.getUser().setPhone(requestManager.getUser().getPhone());
			managerRepository.save(savedManager);

			ManagerResponseDTO managerResponseDTO = managerMapper.toDTO(savedManager);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(managerResponseDTO);
		}
		throw new UserInvalidUpdateException("Atualização invalida");
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

}
