package com.web.desenvolvimento.edusphere.infra.security;

import com.web.desenvolvimento.edusphere.domain.user.Role;
import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private IUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserConfig(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var userAdmin = userRepository.findByUsername("ADMIN");
        userAdmin.ifPresentOrElse(
                user -> System.out.println("Admin já existe"),
                () -> {
                    var user = new User(
                            null,
                            "admin",
                            "admin",
                            "admin",
                            passwordEncoder.encode("123"),
                            "admin@admin.com",
                            "admin1@admin.com",
                            "7798804681",
                            null,
                            Role.ADMIN
                    );
                    userRepository.save(user);
                }

        );
    }
}
