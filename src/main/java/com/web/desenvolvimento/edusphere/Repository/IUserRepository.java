package com.web.desenvolvimento.edusphere.Repository;

import com.web.desenvolvimento.edusphere.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
