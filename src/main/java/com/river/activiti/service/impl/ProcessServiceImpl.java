package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.BackendRoleMapper;
import com.river.activiti.dao.mapper.BackendUserMapper;
import com.river.activiti.dao.mapper.LeaveBillMapper;
import com.river.activiti.filter.ServletFilter;
import com.river.activiti.model.pojo.BackendRole;
import com.river.activiti.model.pojo.BackendUser;
import com.river.activiti.model.pojo.Employee;
import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.service.ProcessService;
import com.river.activiti.utils.Varible;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private BackendRoleMapper backendRoleMapper;

    @Override
    public List<Task> findTaskListByName(String name) {
        List<Task> tasks = null;
        List<BackendRole> backendRoles = backendRoleMapper.queryBackendRoleByUserId(Long.valueOf(name));
        List<String> groups = new ArrayList<String>();
        if (CollectionUtils.isNotEmpty(backendRoles)) {
            for (BackendRole backendRole : backendRoles) {
                groups.add(backendRole.getEnName());
            }
        }
        if (CollectionUtils.isNotEmpty(groups)) {
            tasks = taskService.createTaskQuery().taskCandidateGroupIn(groups).active().list();
        }else {

        }
        // 已经签收的任务
        List<Task> todoList = taskService.createTaskQuery().taskAssignee(name).active().list();
        return tasks;
    }

    @Override
    public LeaveBill queryTaskDetailsByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String businessKey = processInstance.getBusinessKey();
        Long leaveId = null;
        if (StringUtils.isNotBlank(businessKey)) {
            leaveId = Long.valueOf(businessKey.split(":")[1]);
        }
        LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(leaveId);
        return leaveBill;
    }

    @Override
    public List<String> queryOutComeListByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processDessDefinitionId = task.getProcessDefinitionId();
        ProcessDefinitionEntity entity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDessDefinitionId);

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();

        String activityId = processInstance.getActivityId();

        //获取当前活动
        ActivityImpl activity = entity.findActivity(activityId);

        //获取当前活动完成后连线的名称
        List<String> nameList = new ArrayList<>();
        List<PvmTransition> pvmTransitions = activity.getOutgoingTransitions();
        if (pvmTransitions != null && pvmTransitions.size() > 0) {
            for (PvmTransition pvm : pvmTransitions) {
                String name = (String) pvm.getProperty("name");
                if (StringUtils.isNotBlank(name)) {
                    nameList.add(name);
                } else {
                    nameList.add("默认提交");
                }
            }
        }
        return nameList;
    }

    @Override
    public void completeTask(Long leaveBillId, String taskId, String comment, Varible varible) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        taskService.addComment(taskId, processInstanceId, comment);
        //添加审批人
        BackendUser backendUser = (BackendUser) ServletFilter.localRequest.get().getSession().getAttribute("backendUser");
        Authentication.setAuthenticatedUserId(String.valueOf(backendUser.getId()));
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(varible.getKey(), varible.getValue());

        taskService.complete(taskId, variables);

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        if (null == processInstance) {
            LeaveBill leaveBill = leaveBillMapper.selectByPrimaryKey(leaveBillId);
            if ("0".equals(varible.getValue())) {
                leaveBill.setState(3);
            } else {
                leaveBill.setState(2);
            }
            leaveBillMapper.updateByPrimaryKeySelective(leaveBill);
        }
    }

    @Override
    public Map<String, Object> getVarbles(String taskId) {
        return taskService.getVariables(taskId);
    }

    @Override
    public void claim(String taskId, Long id) {
        taskService.claim(taskId,String.valueOf(id));
    }
}
