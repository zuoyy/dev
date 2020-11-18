package com.zuoyy.component.actionLog.action;

import com.zuoyy.component.actionLog.action.base.ActionMap;
import com.zuoyy.component.actionLog.action.base.ResetLog;
import com.zuoyy.modules.system.domain.ActionLog;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 通用：记录物理删除行为
 *
 * @author zuo
 */
@Slf4j
public class DeleteAction extends ActionMap {

    @Override
    public void init() {
        // 记录数据保存日志
        putMethod("default", "defaultMethod");
    }

    /**
     * 重新包装保存的数据行为方法
     *
     * @param resetLog ResetLog对象数据
     */
    public static void defaultMethod(ResetLog resetLog) {
        ActionLog actionLog = resetLog.getActionLog();
        if(resetLog.isSuccessRecord()){
            List<String> title = (List<String>) resetLog.getParam("title");
            resetLog.getActionLog().setMessage(actionLog.getName()+ "：" + title.toString());
        }

    }
}
