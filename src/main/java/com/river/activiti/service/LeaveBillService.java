package com.river.activiti.service;

import com.river.activiti.model.pojo.LeaveBill;

import java.util.List;

/**
 * create by river  2017/11/12
 * desc:
 */
public interface LeaveBillService {

    List<LeaveBill> findLeaveBillByUserId(Long id);

    void addLeaveBill(LeaveBill leaveBill);

    void startProcess(LeaveBill leaveBill);
}
