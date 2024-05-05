package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
