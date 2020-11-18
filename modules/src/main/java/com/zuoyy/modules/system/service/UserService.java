package com.zuoyy.modules.system.service;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.system.query.UserQuery;
import com.zuoyy.modules.common.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserService extends BaseService<User> {

    Page<User> getPageList(UserQuery query);

    List<User> findByQuery(UserQuery query);

    User findByName(String username);

    @Transactional
    Boolean updateStatus(StatusEnum statusEnum, List<String> idList);

    User getUserDetail(String id);

    /**
     * 用户名是否重复
     * @param user 用户对象
     * @return 用户数据
     */
    Boolean repeatByUsername(User user);




}
