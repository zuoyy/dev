package com.zuoyy.modules.system.service;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.common.BaseService;

import java.util.List;

public interface MenuService extends BaseService<Menu> {


    /**
     * 获取排序最大值
     * @param pid 父菜单ID
     */
    Integer getSortMax(String pid);

    /**
     * 根据菜单对象的Example判断是否存在
     * @param menu 菜单对象
     */
    Menu getByMenuToExample(Menu menu);

    /**
     * 获取菜单列表数据
     */
    List<Menu> getListBySortOk();

    /**
     * 根据父级菜单ID获取本级全部菜单
     * @param pid 父菜单ID
     * @param notId 需要排除的菜单ID
     */
    List<Menu> getListByPid(String pid, String notId);

    List<Menu> getListByType(int type);

    List<Menu> getListByPidAndType(String pid, int type);

    Menu findByPerms(String perms);

    boolean repeatByPerms(Menu menu);

    /**
     * 根据父级菜单ID获取本级全部菜单
     * @param pid
     * @return
     */
    List<Menu> getListByPid(String pid);


    Boolean updateStatus(StatusEnum statusEnum, List<String> idList);



}
