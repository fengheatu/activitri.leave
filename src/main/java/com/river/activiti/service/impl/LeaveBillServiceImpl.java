package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.EmployeeMapper;
import com.river.activiti.dao.mapper.LeaveBillMapper;
import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.service.LeaveBillService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by river  2017/11/12
 * desc:
 */

@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    @Resource
    private LeaveBillMapper leaveBillMapper;

    @Resource
    EmployeeMapper employeeMapper;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Override
    public List<LeaveBill> findLeaveBillByUserId(Long id) {
        return leaveBillMapper.findLeaveBillByUserId(id);
    }

    @Override
    public void addLeaveBill(LeaveBill leaveBill) {
        leaveBillMapper.insert(leaveBill);
    }

    @Override
    public void startProcess(LeaveBill leaveBill) {
        leaveBillMapper.updateByPrimaryKeySelective(leaveBill);

        //流程key
        String key = LeaveBill.class.getSimpleName();
        //流程管理业务
        String objId = key +":" + leaveBill.getId();
        Map<String,Object> vars = new HashMap<String,Object>();
        vars.put("inputUser",employeeMapper.selectByPrimaryKey(leaveBill.getUserId()).getName());
        vars.put("objId",objId);
        runtimeService.startProcessInstanceByKey(key,objId,vars);
    }
}
