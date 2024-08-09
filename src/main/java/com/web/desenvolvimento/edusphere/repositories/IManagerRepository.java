package com.web.desenvolvimento.edusphere.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.desenvolvimento.edusphere.domain.manager.Manager;

public interface IManagerRepository extends JpaRepository<Manager, Long> {
	Optional<Manager> findByUsername(String username);
}
