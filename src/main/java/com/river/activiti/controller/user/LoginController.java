package com.river.activiti.controller.user;

import com.river.activiti.model.pojo.Employee;
import com.river.activiti.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: he.feng
 * @date: 11:28 2017/11/8
 * @desc:
 **/
@Controller
@RequestMapping("/employee")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Resource
    private EmployeeService employeeService;



    /**
     *登录
     * @param id
     * @return
     */
    @RequestMapping("login")
    public String login(Long id, HttpServletRequest request) {
        Employee employee = employeeService.findEmployeeById(id);
        request.getSession().setAttribute("employee",employee);
        return "main";
    }

    @RequestMapping("loginout")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("employee");
        try {
            response.sendRedirect("../login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 欢迎页面的左侧页面
     * @return
     */
    @RequestMapping("left")
    public String leftView() {
        return "left";
    }

    /**
     * 欢迎页面的顶部页面
     * @return
     */
    @RequestMapping("top")
    public String topView() {
        return "top";
    }

    /**
     * 欢迎页面
     * @return
     */
    @RequestMapping("welcome")
    public String welcomeView() {
        return "welcome";
    }


}
