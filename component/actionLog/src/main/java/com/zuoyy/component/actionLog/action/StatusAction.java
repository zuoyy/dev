package com.zuoyy.component.actionLog.action;

import com.zuoyy.common.utils.StatusUtil;
import com.zuoyy.component.actionLog.action.base.ActionMap;
import com.zuoyy.component.actionLog.action.base.ResetLog;
import com.zuoyy.common.enums.StatusEnum;

import java.util.List;

/**
 * 通用：记录数据状态的行为
 *
 * @author zuo
 */
public class StatusAction extends ActionMap {

    @Override
    public void init() {
        // 记录数据状态改变日志
        putMethod("default", "defaultMethod");
    }

    /**
     * 重新包装保存的数据行为方法
     *
     * @param resetLog ResetLog对象数据
     */
    @SuppressWarnings("unchecked")
    public static void defaultMethod(ResetLog resetLog) {
        if(resetLog.isSuccessRecord()){
            String param = (String) resetLog.getParam("param");
            StatusEnum statusEnum = StatusUtil.getStatusEnum(param);
            List<String> ids = (List<String>) resetLog.getParam("ids");
            resetLog.getActionLog().setMessage(statusEnum.message + "ID：" + ids.toString());
        }
    }
}
