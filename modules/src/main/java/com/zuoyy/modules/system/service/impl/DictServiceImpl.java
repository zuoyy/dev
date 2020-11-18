package com.zuoyy.modules.system.service.impl;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Dict;
import com.zuoyy.modules.system.query.DictQuery;
import com.zuoyy.modules.system.repository.DictRepository;
import com.zuoyy.modules.system.service.DictService;
import com.zuoyy.modules.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService {

    @Autowired
    private DictRepository dictRepository;


    /**
     * 页面列表
     * @param query
     * @return
     */
    @Override
    public Page<Dict> getPageList(DictQuery query) {
        Pageable pageable = PageRequest.of(query.getPageIndex(), query.getPageSize(), new Sort(query.getSort(),query.getColumn()));
        return dictRepository.findAll(query,pageable);
    }


    /**
     * 根据字典标识获取字典数据
     * @param name 字典标识
     */
    @Override
    public Dict findByName(String name){
        return dictRepository.findByNameAndStatus(name, StatusEnum.OK.code);
    }

    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<String> idList){
        return dictRepository.updateStatus(statusEnum.code,idList) > 0;
    }

    @Override
    public boolean repeatByName(Dict dict) {
        String id = "";
        if(!StringUtils.isEmpty(dict.getId())){
            id =  dict.getId();
        }
        return dictRepository.findByNameAndIdNot(dict.getName(), id) != null;
    }


}
