package com.zuoyy.modules.system.service.impl;

import com.zuoyy.modules.system.domain.ActionLog;
import com.zuoyy.modules.system.query.ActionLogQuery;
import com.zuoyy.modules.system.repository.ActionLogRepository;
import com.zuoyy.modules.system.service.ActionLogService;
import com.zuoyy.modules.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionLogServiceImpl extends BaseServiceImpl<ActionLog> implements ActionLogService {

    @Autowired
    private ActionLogRepository actionLogRepository;


    /**
     * 页面列表
     * @param query
     * @return
     */
    @Override
    public Page<ActionLog> getPageList(ActionLogQuery query) {
        Pageable pageable = PageRequest.of(query.getPageIndex(), query.getPageSize(), new Sort(query.getSort(),query.getColumn()));
        return actionLogRepository.findAll(query,pageable);
    }

    /**
     * 获取数据的日志列表
     * @param model 模型（表名）
     * @param recordId 数据ID
     */
    @Override
    public List<ActionLog> getDataLogList(String model, String recordId){
        return actionLogRepository.findByModelAndRecordId(model, recordId);
    }


}
