package com.river.activiti.service;

import com.river.activiti.model.pojo.LeaveBill;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author: he.feng
 * @date: 10:40 2017/11/13
 * @desc:
 **/
public interface ProcessService {
    List<Task> findTaskListByName(String name);

    LeaveBill queryTaskDetailsByTaskId(String taskId);

    List<String> queryOutComeListByTaskId(String taskId);
}
