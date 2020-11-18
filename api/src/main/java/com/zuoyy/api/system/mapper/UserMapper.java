package com.zuoyy.api.system.mapper;

import com.zuoyy.api.system.dto.UserDTO;
import com.zuoyy.modules.system.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({})

    UserDTO domainToDto(User user);
    List<UserDTO> domainToDto(List<User> user);

}
