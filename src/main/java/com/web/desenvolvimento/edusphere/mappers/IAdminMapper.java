package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.admin.Admin;
import com.web.desenvolvimento.edusphere.dto.admin.AdminRequest;
import com.web.desenvolvimento.edusphere.dto.admin.AdminResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IAdminMapper {

    IAdminMapper INSTANCE = Mappers.getMapper(IAdminMapper.class);

    Admin toModel(AdminRequest adminRequest);
    AdminResponse toDTO(Admin admin);

}
