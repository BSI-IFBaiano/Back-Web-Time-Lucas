package com.web.desenvolvimento.edusphere.services.admin;

import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.dto.admin.AdminRequest;
import com.web.desenvolvimento.edusphere.dto.admin.AdminResponse;
import com.web.desenvolvimento.edusphere.mappers.IAdminMapper;
import com.web.desenvolvimento.edusphere.repositories.IAdminRepository;
import com.web.desenvolvimento.edusphere.repositories.IUserRepository;
import com.web.desenvolvimento.edusphere.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AdminService {
    private IAdminRepository adminRepository;
    private IUserRepository userRepository;
    private final IAdminMapper adminMapper = IAdminMapper.INSTANCE;


    public AdminService(IAdminRepository adminRepository, IUserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<AdminResponse> createAdmin(AdminRequest adminRequest){
        User adminUser = userRepository.findById(adminRequest.idUser()).orElseThrow(
                ()-> new RuntimeException("Usuário não existe")
        );
        var adminToSave = adminMapper.toModel(adminRequest);
        adminToSave.setUser(adminUser);
        adminRepository.save(adminToSave);
        var adminResponse = adminMapper.toDTO(adminToSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponse);
    }
}