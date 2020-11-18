package com.zuoyy.component.thymeleaf.utility;

import com.zuoyy.common.utils.EntityBeanUtil;
import com.zuoyy.common.utils.SpringContextUtil;
import com.zuoyy.modules.system.domain.ActionLog;
import com.zuoyy.modules.system.service.ActionLogService;

import javax.persistence.Table;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class LogUtil {

    /**
     * 获取实体对象的日志
     * @param entity 实体对象
     */
    public List<ActionLog> entityList(Object entity){
        ActionLogService actionLogService = SpringContextUtil.getBean(ActionLogService.class);
        Table table = entity.getClass().getAnnotation(Table.class);
        String tableName = table.name();
        try {
            Object object = EntityBeanUtil.getField(entity, "id");
            String entityId = String.valueOf(object);
            return actionLogService.getDataLogList(tableName, entityId);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
