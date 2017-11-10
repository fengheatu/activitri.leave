package com.river.activiti.service;

import com.river.activiti.model.pojo.Leave; /**
 * @author: he.feng
 * @date: 10:43 2017/11/9
 * @desc:
 **/
public interface LeaveService {

    /**
     * 请假
     * @param leave
     */
    void add(Leave leave);
}
