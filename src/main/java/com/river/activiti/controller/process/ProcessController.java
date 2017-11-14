package com.river.activiti.controller.process;

import com.river.activiti.model.pojo.Employee;
import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author: he.feng
 * @date: 10:01 2017/11/7
 * @desc:  部署流程
 **/
@Controller
@RequestMapping("/deploy")
public class ProcessController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessController.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessService processService;


    /**
     *@desc:
     *@create by he.feng
     *@date 2017/11/7 10:08
     * @param redirectAttributes
     *@return java.lang.String
     */
    @RequestMapping("list")
    public ModelAndView  deployList(ModelAndView modelAndView) {
        List<Deployment> deployments = repositoryService
                .createDeploymentQuery().list();
        modelAndView.addObject("deployments",deployments);
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        modelAndView.addObject("processDefinitions",list);
        modelAndView.setViewName("workflow/workflow");
        return modelAndView;
    }

    /**
     * 删除流程定义
     * @param deploymentId
     * @param modelAndView
     * @return
     */
    @RequestMapping("/delete")
    public ModelAndView delete(String deploymentId,ModelAndView modelAndView) {
        repositoryService.deleteDeployment(deploymentId,true);
        modelAndView.setViewName("workflow/workflow");
        return modelAndView;
    }


    /**
     *
     * @param deploymentId
     * @param imageName
     * @param request
     * @param deploymentId
     * @param imageName
     */
    @RequestMapping("/showImage")
    public void showImage(HttpServletResponse response, HttpServletRequest request,String deploymentId, String imageName) {
        InputStream in = repositoryService.getResourceAsStream(deploymentId,imageName);
        response.setContentType("image/jpeg");
        try {
            ServletOutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/task/list")
    public ModelAndView taskList(ModelAndView modelAndView,HttpServletRequest request) {
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (null == employee) {
            throw new RuntimeException("no login");
        }
        List<Task> tasks = processService.findTaskListByName(employee.getName());
        modelAndView.addObject("tasks",tasks);
        modelAndView.setViewName("workflow/task");
         return modelAndView;
    }

    @RequestMapping("/task/details")
    public ModelAndView handleTask(ModelAndView modelAndView,HttpServletRequest request,String taskId) {
        LeaveBill leaveBill = processService.queryTaskDetailsByTaskId(taskId);
        List<String> outcomeList = processService.queryOutComeListByTaskId(taskId);
        modelAndView.addObject("leaveBill",leaveBill);
        modelAndView.addObject("taskId",taskId);
        modelAndView.setViewName("workflow/taskForm");
        return modelAndView;
    }


}
