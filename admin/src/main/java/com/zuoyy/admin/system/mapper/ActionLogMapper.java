package com.zuoyy.admin.system.mapper;

import com.zuoyy.admin.system.dto.ActionLogDTO;
import com.zuoyy.modules.system.domain.ActionLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ActionLogMapper {

    ActionLogMapper INSTANCE = Mappers.getMapper(ActionLogMapper.class);

    @Mappings({})
    ActionLogDTO domainToDto(ActionLog actionLog);
    List<ActionLogDTO> domainToDto(List<ActionLog> user);

}
