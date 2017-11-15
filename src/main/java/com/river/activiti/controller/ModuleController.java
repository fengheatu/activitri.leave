package com.river.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
@RequestMapping("/model")
public class ModuleController {

    private Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    private RepositoryService repositoryService;

    @RequestMapping(value = "create")
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setKey(StringUtils.defaultString(UUID.randomUUID().toString()));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            logger.error("创建模型失败：", e);
        }
    }

	@RequestMapping("/download/bpmn")
    public void downBpmn(String modelId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Model modelData = repositoryService.getModel(modelId);
        ObjectNode modelNode = (ObjectNode) objectMapper.readTree(repositoryService.getModelEditorSource(modelData.getId()));
        byte[] bpmnBytes = null;
        BpmnModel model2 = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        bpmnBytes = new BpmnXMLConverter().convertToXML(model2);
        String processName = modelData.getName() + ".bpmn20.xml";
        File file = new File("d:/" + processName);
        FileOutputStream os = new FileOutputStream(file);
        os.write(bpmnBytes);
    }

    @RequestMapping("/modify/process/{modelId}")
    public void modifyProcess(HttpServletRequest request,HttpServletResponse response,@PathVariable("modelId") String modleId) {
        try {
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modleId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
