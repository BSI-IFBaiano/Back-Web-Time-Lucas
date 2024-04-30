package com.web.desenvolvimento.edusphere.Mapper;

import com.web.desenvolvimento.edusphere.DTO.UserDTO;
import com.web.desenvolvimento.edusphere.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toModel(UserDTO userDTO);

    @Mapping(target = "idUser", source = "user.idUser")
    UserDTO toDTO(User user);

    @Mapping(target = "idUser", source = "user.idUser")
    List<UserDTO> toDTO(List<User> users);
}
