package com.river.activiti.service;

import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.utils.Varible;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * @author: he.feng
 * @date: 10:40 2017/11/13
 * @desc:
 **/
public interface ProcessService {
    List<Task> findTaskListByName(String name);

    LeaveBill queryTaskDetailsByTaskId(String taskId);

    List<String> queryOutComeListByTaskId(String taskId);

    void completeTask(Long leaveBillId, String taskId, String comment, Varible varible);

    Map<String, Object> getVarbles(String taskId);

    void claim(String taskId, Long id);
}
