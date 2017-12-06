package com.river.activiti.controller.leave;

import com.river.activiti.dao.mapper.LeaveBillMapper;
import com.river.activiti.model.pojo.BackendUser;
import com.river.activiti.model.pojo.Employee;
import com.river.activiti.model.pojo.LeaveBill;
import com.river.activiti.service.LeaveBillService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.collections.CollectionUtils;
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
    private LeaveBillService leaveBillService;

    /**
     * 跳转到假单填写页面
     *
     * @return
     */
    @RequestMapping("/addView")
    public ModelAndView leaveView(ModelAndView modelAndView) {
        modelAndView.setViewName("leaveBill/input");
        return modelAndView;
    }

    /**
     * 假单列表
     *
     * @param request
     * @param modelAndView
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest request, ModelAndView modelAndView) {
        BackendUser backendUser = (BackendUser) request.getSession().getAttribute("backendUser");
        if (backendUser == null) {
            throw new RuntimeException("没登录");
        }
        List<LeaveBill> leaveBills = leaveBillService.findLeaveBillByUserId(backendUser.getId());
        modelAndView.addObject("leaveBills", leaveBills);
        modelAndView.setViewName("leaveBill/list");
        return modelAndView;
    }

    /**
     * 添加假单信息
     *
     * @param request
     * @param modelAndView
     * @param leaveBill
     * @return
     */
    @RequestMapping("/add")
    public ModelAndView add(HttpServletRequest request, ModelAndView modelAndView, LeaveBill leaveBill) {
        BackendUser backendUser = (BackendUser) request.getSession().getAttribute("backendUser");
        leaveBill.setState(0);
        leaveBill.setUserId(backendUser.getId());
        leaveBillService.addLeaveBill(leaveBill);
        modelAndView.addObject("leaveBills", leaveBillService.findLeaveBillByUserId(backendUser.getId()));
        modelAndView.setViewName("leaveBill/list");
        return modelAndView;
    }


    /**
     * 启动流程，业务与流程关联
     *
     * @param request
     * @param modelAndView
     * @param id
     * @return
     */
    @RequestMapping("/startProcess")
    public ModelAndView startProcess(HttpServletRequest request, ModelAndView modelAndView, Long id) {
        BackendUser backendUser = (BackendUser) request.getSession().getAttribute("backendUser");
        LeaveBill leaveBill = new LeaveBill();
        leaveBill.setId(id);
        leaveBill.setState(1);
        leaveBill.setUserId(backendUser.getId());
        leaveBillService.startProcess(leaveBill);
        modelAndView.addObject("leaveBills", leaveBillService.findLeaveBillByUserId(backendUser.getId()));
        modelAndView.setViewName("leaveBill/list");
        return modelAndView;
    }

}
