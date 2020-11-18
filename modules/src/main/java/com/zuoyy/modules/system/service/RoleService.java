package com.zuoyy.modules.system.service;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.query.RoleQuery;
import com.zuoyy.modules.common.BaseService;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface RoleService extends BaseService<Role> {

    Page<Role> getPageList(RoleQuery query);

    List<Role> findByQuery(RoleQuery query);

    Role findByName(String name);

    /**
     * 获取用户角色列表
     * @param id 用户ID
     */
    Set<Role> getUserOkRoleList(String id);

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     */
    Boolean existsUserOk(String id);

    boolean repeatByName(Role role);

    Boolean updateStatus(StatusEnum statusEnum, List<String> idList);


}
