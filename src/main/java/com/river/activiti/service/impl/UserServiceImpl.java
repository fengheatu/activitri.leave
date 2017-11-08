package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.UserMapper;
import com.river.activiti.model.pojo.User;
import com.river.activiti.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: he.feng
 * @date: 13:33 2017/11/8
 * @desc:
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    /**
     * create by he.feng
     *
     * @param userId
     * @return
     */
    @Override
    public User findUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
