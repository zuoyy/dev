package com.zuoyy.modules.system.repository;

import com.zuoyy.modules.system.domain.Role;
import com.zuoyy.modules.common.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends BaseRepository<Role,String> {

    Role findByName(String name);

    /**
     * 查找多个角色
     * @param ids id列表
     */
    List<Role> findByIdIn(List<String> ids);

    /**
     * 查找相应状态的角色
     * @param sort 排序对象
     */
    List<Role> findAllByStatus(Sort sort, int status);

    /**
     * 查询指定用户的角色列表
     * @param id 用户ID
     * @param status 角色状态
     */
    Set<Role> findByUsers_IdAndStatus(String id, int status);

    /**
     * 判断指定的用户是否存在角色
     * @param id 用户ID
     * @param status 角色状态
     */
    Boolean existsByUsers_IdAndStatus(String id, int status);

    Role findByNameAndIdNot(String name, String id);

    /**
     * 取消角色与用户之间的关系
     * @param ids 角色ID
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sys_user_role WHERE role_id in ?1", nativeQuery = true)
    Integer cancelUserJoin(List<String> ids);

    /**
     * 取消角色与菜单之间的关系
     * @param ids 角色ID
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sys_role_menu WHERE role_id in ?1", nativeQuery = true)
    Integer cancelMenuJoin(List<String> ids);



}
