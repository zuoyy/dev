package com.zuoyy.modules.system.repository;

import com.zuoyy.modules.system.domain.Dept;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.common.BaseRepository;

import java.util.List;

public interface UserRepository extends BaseRepository<User, String> {

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 用户数据
     */
    User findByUsername(String username);

    /**
     * 根据用户名查询用户数据,且排查指定ID的用户
     * @param username 用户名
     * @param id 排除的用户ID
     * @return 用户数据
     */
    User findByUsernameAndIdNot(String username, String id);

    /**
     * 查找多个相应部门的用户列表
     */
    List<User> findByDept(Dept dept);

    /**
     * 删除多条数据
     * @param ids     ID列表
     */
    Integer deleteByIdIn(List<String> ids);
}
