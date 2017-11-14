package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.LeaveBillMapper;
import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: he.feng
 * @date: 10:40 2017/11/13
 * @desc:
 **/

@Service
public class ProcessServiceImpl implements ProcessService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private LeaveBillMapper leaveBillMapper;

    @Override
    public List<Task> findTaskListByName(String name) {
       return  taskService.createTaskQuery().taskAssignee(name).list();
    }

    @Override
    public LeaveBill queryTaskDetailsByTaskId(String taskId) {
       Task task =  taskService.createTaskQuery().taskId(taskId).singleResult();
       String processInstanceId = task.getProcessInstanceId();
       ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
               .processInstanceId(processInstanceId).singleResult();
       String businessKey = processInstance.getBusinessKey();
       Long  leaveId = null;
       if(StringUtils.isNotBlank(businessKey)) {
           leaveId = Long.valueOf(businessKey.split(":")[1]);
       }
       LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(leaveId);
        return leaveBill;
    }

    @Override
    public List<String> queryOutComeListByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDessDefinitionId  = task.getProcessDefinitionId();
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDessDefinitionId);

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processDessDefinitionId)
                .singleResult();

        String activityId = processInstance.getActivityId();

        //获取当前活动
        ActivityImpl activity = entity.findActivity(activityId);

        //获取当前活动完成后连线的名称
        List<String> nameList = new ArrayList<>();
        List<PvmTransition> pvmTransitions = activity.getOutgoingTransitions();
        if(pvmTransitions != null && pvmTransitions.size() > 0) {
            for (PvmTransition pvm : pvmTransitions) {
                String name = (String) pvm.getProperty("name");
                if(StringUtils.isNotBlank(name)) {
                    nameList.add(name);
                }else {
                    nameList.add("默认提交");
                }
            }
        }
        return nameList;
    }
}
