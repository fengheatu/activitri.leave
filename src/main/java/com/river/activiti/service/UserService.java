package com.river.activiti.service;

import com.river.activiti.model.pojo.User;

/**
 * @author: he.feng
 * @date: 13:32 2017/11/8
 * @desc:
 **/
public interface UserService {

    /**
     * create by he.feng
     * @param userId
     * @return
     */
    User findUserById(Long userId);
}
