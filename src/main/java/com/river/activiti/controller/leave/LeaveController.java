package com.river.activiti.controller.leave;

import com.river.activiti.model.pojo.Leave;
import com.river.activiti.model.pojo.User;
import com.river.activiti.service.LeaveService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: he.feng
 * @date: 9:24 2017/11/9
 * @desc:
 **/
@Controller
@RequestMapping("/leave")
public class LeaveController {

    private static final Logger logger = LoggerFactory.getLogger(LeaveController.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private LeaveService leaveService;

    /**
     *
     * @return
     */
    @RequestMapping("/leaveView")
    public ModelAndView leaveView(ModelAndView modelAndView) {
        //获取最新的请假流程
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("leave")
                .orderByProcessDefinitionVersion().asc()
                .list();
        Map<String,ProcessDefinition> map = new LinkedHashMap<String,ProcessDefinition>();
        for(ProcessDefinition pd : list) {
            map.put(pd.getKey(),pd);
        }
        List<ProcessDefinition> list1 = (List<ProcessDefinition>) map.values();
        modelAndView.addObject("processDefinitions",list1);
        modelAndView.setViewName("leaveBill/input");
        return modelAndView;
    }

    /**
     * 请假
     * @param request
     * @param leave
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public String leave(HttpServletRequest request, Leave leave) {
        User user = (User) request.getAttribute("user");
        Long userId = user.getId();
        leave.setCreateBy(userId);
        leave.setUpdateBy(userId);
        Date date = new Date();
        leave.setEndTime(date);
        leave.setGmtCreate(date);
        leave.setGmtModified(date);
        leave.setStartTime(date);
        leave.setStatus(1);
        leaveService.add(leave);
        return "leaveBill/input";
    }
}
