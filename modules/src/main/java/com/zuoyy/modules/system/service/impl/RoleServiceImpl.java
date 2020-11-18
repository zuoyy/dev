package com.zuoyy.modules.system.service.impl;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.system.query.RoleQuery;
import com.zuoyy.modules.system.repository.RoleRepository;
import com.zuoyy.modules.system.service.RoleService;
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
import java.util.Set;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Page<Role> getPageList(RoleQuery query) {
        Pageable pageable = PageRequest.of(query.getPageIndex(), query.getPageSize(), new Sort(query.getSort(),query.getColumn()));
        return roleRepository.findAll(query,pageable);
    }

    @Override
    public List<Role> findByQuery(RoleQuery query) {
        return roleRepository.findAll(query);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    /**
     * 获取用户角色列表
     * @param id 用户ID
     */
    @Override
    @Transactional
    public Set<Role> getUserOkRoleList(String id) {
        int status = StatusEnum.OK.code;
        return roleRepository.findByUsers_IdAndStatus(id, status);
    }

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     * @return
     */
    @Override
    public Boolean existsUserOk(String id) {
        int status = StatusEnum.OK.code;
        return roleRepository.existsByUsers_IdAndStatus(id, status);
    }


    @Override
    public boolean repeatByName(Role role) {
        String id = "";
        if(!StringUtils.isEmpty(role.getId())){
            id =  role.getId();
        }
        return roleRepository.findByNameAndIdNot(role.getName(), id) != null;
    }

    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<String> idList){
        // 删除角色时取消与角色和菜单的关联
        if(statusEnum == StatusEnum.DELETE){
            /*List<Role> roles = roleRepository.findByIdIn(ids);
            roles.forEach(role -> {
                role.setMenus(null);
                role.getUsers().forEach(user -> user.getRoles().remove(role));
            });*/

            // 非规范的Jpa操作，直接采用SQL语句
            roleRepository.cancelUserJoin(idList);
            roleRepository.cancelMenuJoin(idList);
        }
        return roleRepository.updateStatus(statusEnum.code,idList) > 0;
    }

}
