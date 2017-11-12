package com.river.activiti.controller.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.jmx.LoggerDynamicMBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.jws.WebParam;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: he.feng
 * @date: 10:01 2017/11/7
 * @desc:  部署流程
 **/
@Controller
@RequestMapping("/deploy")
public class DeployController {

    private static final Logger logger = LoggerFactory.getLogger(DeployController.class);

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;


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
}
