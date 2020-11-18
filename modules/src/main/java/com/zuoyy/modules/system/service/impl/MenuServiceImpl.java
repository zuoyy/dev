package com.zuoyy.modules.system.service.impl;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.Menu;
import com.zuoyy.modules.system.repository.MenuRepository;
import com.zuoyy.modules.system.service.MenuService;
import com.zuoyy.modules.common.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

    @Autowired
    private MenuRepository menuRepository;


    /**
     * 获取排序最大值
     * @param pid 父菜单ID
     */
    @Override
    public Integer getSortMax(String pid){
        return menuRepository.findSortMax(pid);
    }

    /**
     * 根据菜单对象的Example判断是否存在
     * @param menu 菜单对象
     */
    @Override
    public Menu getByMenuToExample(Menu menu) {
        return menuRepository.findOne(Example.of(menu)).orElse(null);
    }

    /**
     * 获取菜单列表数据
     */
    @Override
    public List<Menu> getListBySortOk() {
        Sort sort = new Sort(Sort.Direction.ASC, "type", "sort");
        return menuRepository.findAllByStatus(sort, StatusEnum.OK.code);
    }

    /**
     * 根据父级菜单ID获取本级全部菜单
     * @param pid 父菜单ID
     * @param notId 需要排除的菜单ID
     */
    @Override
    public List<Menu> getListByPid(String pid, String notId){
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return menuRepository.findByPidAndIdNotAndStatus(sort, pid, notId,StatusEnum.OK.code);
    }

    @Override
    public List<Menu> getListByType(int type) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return menuRepository.findByTypeAndStatus(sort,type,StatusEnum.OK.code);
    }

    @Override
    public List<Menu> getListByPidAndType(String pid, int type) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return menuRepository.findByPidAndTypeAndStatus(sort,pid,type,StatusEnum.OK.code);
    }

    @Override
    public Menu findByPerms(String perms) {
        return menuRepository.findByPermsAndStatus(perms,StatusEnum.OK.code);
    }

    @Override
    public boolean repeatByPerms(Menu menu) {
        String id = "";
        if(!StringUtils.isEmpty(menu.getId())){
            id =  menu.getId();
        }
        return menuRepository.findByPermsAndIdNot(menu.getPerms(), id) != null;
    }

    @Override
    public List<Menu> getListByPid(String pid) {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return menuRepository.findByPid(sort, pid);
    }

    /**
     * 状态(启用，冻结，删除)/批量状态处理
     */
    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<String> ids){
        // 获取与之关联的所有菜单
        Set<Menu> treeMenus = new HashSet<>();
        List<Menu> menus = menuRepository.findByIdIn(ids);
        menus.forEach(menu -> {
            treeMenus.add(menu);
            treeMenus.addAll(menuRepository.findByPidsLikeAndStatus("%[" + menu.getId() + "]%", menu.getStatus()));
        });

        treeMenus.forEach(menu -> {
            // 删除菜单状态时，同时更新角色的权限
            if(statusEnum == StatusEnum.DELETE){
                /*menu.getRoles().forEach(role -> {
                    role.getMenus().remove(menu);
                });*/
                // 非规范的Jpa操作，直接采用SQL语句
                menuRepository.cancelRoleJoin(menu.getId());
            }
            // 更新关联的所有菜单状态
            menu.setStatus(statusEnum.code);
        });

        return treeMenus.size() > 0;
    }
}
