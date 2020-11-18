package com.zuoyy.modules.system.repository;

import com.zuoyy.common.constant.StatusConst;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.common.BaseRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MenuRepository extends BaseRepository<Menu, String> {

    /**
     * 查找多个菜单
     * @param ids id列表
     */
    List<Menu> findByIdIn(List<String> ids);

    /**
     * 查找响应状态的菜单
     * @param sort 排序对象
     */
    List<Menu> findAllByStatus(Sort sort, int status);

    /**
     * 根据父ID查找子菜单
     * @param pids pid列表
     */
    List<Menu> findByPidsLikeAndStatus(String pids, int status);


    /**
     * 获取排序最大值
     * @param pid 父菜单ID
     */
    @Query("select max(sort) from Menu m where m.pid = ?1 and m.status <> " + StatusConst.DELETE)
    Integer findSortMax(String pid);

    /**
     * 根据父级菜单ID获取本级全部菜单
     * @param sort 排序对象
     * @param pid 父菜单ID
     * @param notId 需要排除的菜单ID
     */
    List<Menu> findByPidAndIdNotAndStatus(Sort sort, String pid, String notId,int status);


    List<Menu> findByTypeAndStatus(Sort sort,int type,int status);

    List<Menu> findByPidAndTypeAndStatus(Sort sort, String pid, int type,int status);

    Menu findByPermsAndStatus(String perms,int status);

    Menu findByPermsAndIdNot(String perms,String id);

    /**
     * 根据父级菜单ID获取本级全部菜单
     * @param sort 排序对象
     * @param pid 父菜单ID
     */
    List<Menu> findByPid(Sort sort, String pid);

    /**
     * 取消菜单与角色之间的关系
     * @param id 菜单ID
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sys_role_menu WHERE menu_id = ?1", nativeQuery = true)
    Integer cancelRoleJoin(String id);
}
