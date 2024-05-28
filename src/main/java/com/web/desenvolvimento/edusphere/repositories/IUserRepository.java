package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
