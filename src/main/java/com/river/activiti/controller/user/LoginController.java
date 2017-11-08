package com.river.activiti.controller.user;

import com.river.activiti.model.pojo.User;
import com.river.activiti.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: he.feng
 * @date: 11:28 2017/11/8
 * @desc:
 **/
@Controller
@RequestMapping("/user")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private UserService userService;


    /**
     *登录
     * @param userId
     * @return
     */
    @RequestMapping("login")
    public String login(Long userId, HttpServletRequest request) {
        logger.info("userId: " + userId);
        User user = userService.findUserById(userId);
        request.getSession().setAttribute("user",user);
        return "main";
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
