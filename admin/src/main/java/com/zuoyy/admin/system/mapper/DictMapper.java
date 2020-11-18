package com.zuoyy.admin.system.mapper;

import com.zuoyy.admin.system.dto.DictDTO;
import com.zuoyy.modules.system.domain.Dict;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DictMapper {

    DictMapper INSTANCE = Mappers.getMapper(DictMapper.class);

    @Mappings({})
    DictDTO domainToDto(Dict dict);
    List<DictDTO> domainToDto(List<Dict> user);

}
