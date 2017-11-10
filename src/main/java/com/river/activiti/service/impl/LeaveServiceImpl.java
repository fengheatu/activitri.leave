package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.LeaveMapper;
import com.river.activiti.model.pojo.Leave;
import com.river.activiti.service.LeaveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: he.feng
 * @date: 10:44 2017/11/9
 * @desc:
 **/
@Service
public class LeaveServiceImpl implements LeaveService {


    @Resource
    private LeaveMapper leaveMapper;
    /**
     * 请假
     *
     * @param leave
     */
    @Override
    public void add(Leave leave) {
        leaveMapper.insert(leave);
    }
}
