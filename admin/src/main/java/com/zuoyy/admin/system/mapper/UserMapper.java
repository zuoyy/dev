package com.zuoyy.admin.system.mapper;

import com.zuoyy.admin.system.dto.UserDTO;
import com.zuoyy.modules.system.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({})
    UserDTO domainToDto(User user);
    User dtoToDomain(UserDTO dto);
    List<UserDTO> domainToDto(List<User> user);


}
