package com.zuoyy.modules.system.service;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Dict;
import com.zuoyy.modules.system.query.DictQuery;
import com.zuoyy.modules.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;


public interface DictService extends BaseService<Dict> {

    Page<Dict> getPageList(DictQuery query);

    Dict findByName(String name);

    Boolean updateStatus(StatusEnum statusEnum, List<String> idList);

    boolean repeatByName(Dict dict);


}
