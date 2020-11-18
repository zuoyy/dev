package com.zuoyy.component.actionLog.action;

import com.zuoyy.component.actionLog.action.base.ActionMap;
import com.zuoyy.component.actionLog.action.base.ResetLog;
import com.zuoyy.component.actionLog.action.model.BusinessMethod;
import com.zuoyy.modules.system.domain.Role;

import javax.persistence.Table;

/**
 * 角色日志行为
 * @author zuo
 */
public class RoleAction extends ActionMap {

    public static final String ROLE_SAVE = "role_save";
    public static final String ROLE_AUTH = "role_auth";

    @Override
    public void init() {
        // 保存角色行为
        putMethod(ROLE_SAVE, new BusinessMethod("角色管理","roleSave"));
        // 角色授权行为
        putMethod(ROLE_AUTH, new BusinessMethod("角色授权","roleAuth"));
    }

    // 保存角色行为方法
    public void roleSave(ResetLog resetLog){
        resetLog.getActionLog().setMessage("角色成功：${title}");
        SaveAction.defaultMethod(resetLog);
    }

    // 角色授权行为方法
    public void roleAuth(ResetLog resetLog){
        Role role = (Role) resetLog.getParam("role");
        Table table = Role.class.getAnnotation(Table.class);
        resetLog.getActionLog().setModel(table.name());
        resetLog.getActionLog().setRecordId(role.getId());
        if (resetLog.isSuccess()){
            resetLog.getActionLog().setMessage("角色授权成功："+role.getTitle());
        }else {
            resetLog.getActionLog().setMessage("角色授权失败："+role.getTitle());
        }
    }
}
