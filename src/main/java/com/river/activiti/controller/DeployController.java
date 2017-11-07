package com.river.activiti.controller;

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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author: he.feng
 * @date: 10:01 2017/11/7
 * @desc:  部署流程
 **/
@Controller
@RequestMapping("/deploy")
public class DeployController {

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
     * @param modelId
     * @param redirectAttributes
     *@return java.lang.String
     */
    @RequestMapping("/model/{modelId}")
    @ResponseBody
    public void  modelDeploy(@PathVariable("modelId") String modelId) {
        Model modelData = repositoryService.getModel(modelId);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ObjectNode modelNode = (ObjectNode) objectMapper.readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;
            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";

            //部署流程
            Deployment deployment = repositoryService.createDeployment()
                    .name(modelData.getName())
                    .addString(processName, StringUtils.toEncodedString(bpmnBytes, Charset.forName("UTF-8")))
                    .deploy();
            //查找流程定义
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).singleResult();

            //启动流程
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());


            Task task =    taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();;
            while (task != null){
                taskService.complete(task.getId());
                System.out.println(task.getName()+"  " + task.getAssignee() + " complete");
                task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
