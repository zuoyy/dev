package com.zuoyy.modules.system.service;

import com.zuoyy.modules.system.domain.ActionLog;
import com.zuoyy.modules.system.query.ActionLogQuery;
import com.zuoyy.modules.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ActionLogService extends BaseService<ActionLog> {

    Page<ActionLog> getPageList(ActionLogQuery query);


    /**
     * 获取数据的日志列表
     * @param model 模型（表名）
     * @param recordId 数据ID
     */
    List<ActionLog> getDataLogList(String model, String recordId);


}
