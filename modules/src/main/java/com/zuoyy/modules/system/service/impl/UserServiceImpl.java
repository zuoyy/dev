package com.zuoyy.modules.system.service.impl;

import com.zuoyy.common.enums.StatusEnum;
import com.zuoyy.modules.system.domain.User;
import com.zuoyy.modules.system.query.UserQuery;
import com.zuoyy.modules.system.repository.UserRepository;
import com.zuoyy.modules.system.service.UserService;
import com.zuoyy.modules.common.BaseServiceImpl;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {


    @Autowired
    private UserRepository userRepository;


    /**
     * 页面列表
     * @param query
     * @return
     */
    @Override
    public Page<User> getPageList(UserQuery query) {
        Pageable pageable = PageRequest.of(query.getPageIndex(), query.getPageSize(), new Sort(query.getSort(),query.getColumn()));
        return userRepository.findAll(query,pageable);
    }

    @Override
    public List<User> findByQuery(UserQuery query) {
        return userRepository.findAll(query);
    }

    /**
     *
     * @param username 用户名
     * @return
     */
    @Override
    public User findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public Boolean updateStatus(StatusEnum statusEnum, List<String> ids){
        // 联级删除与角色之间的关联
        if(statusEnum == StatusEnum.DELETE){
            return userRepository.deleteByIdIn(ids) > 0;
        }
        return userRepository.updateStatus(statusEnum.code, ids) > 0;
    }

    @Override
    public User getUserDetail(String id) {
        Optional<User> optional =  userRepository.findById(id);
        if(optional.isPresent()){
            User user =  optional.get();
            if (Hibernate.isInitialized(user)) {
                Hibernate.initialize(user);
            }
            return user;
        }
        return null;
    }

    @Override
    public Boolean repeatByUsername(User user) {
        String id = "";
        if(!StringUtils.isEmpty(user.getId())){
            id =  user.getId();
        }
        return userRepository.findByUsernameAndIdNot(user.getUsername(), id) != null;
    }
}
