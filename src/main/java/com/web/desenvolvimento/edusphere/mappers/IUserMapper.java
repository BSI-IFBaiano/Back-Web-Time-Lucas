package com.web.desenvolvimento.edusphere.mappers;

import com.web.desenvolvimento.edusphere.domain.user.User;
import com.web.desenvolvimento.edusphere.dto.user.UserRequestDTO;
import com.web.desenvolvimento.edusphere.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper

public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toModel(UserRequestDTO userRequestDTO);
    UserResponseDTO toDTO(User user);
    List<UserResponseDTO> toDTO(List<User> users);
}
