package com.river.activiti.listener;

import com.river.activiti.filter.ServletFilter;
import com.river.activiti.model.pojo.Employee;
import com.river.activiti.service.EmployeeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: he.feng
 * @date: 9:24 2017/11/15
 * @desc:
 **/
public class ManagerTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        /*activiti引擎将会在内部用Class.newInstance(..)方法创建一个该类的对象，
        这个对象并不spring容器管理，所以无法获取spring容器给我们生成的bean；*/
        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        EmployeeService employeeService = (EmployeeService) ac.getBean("employeeService");

        HttpServletRequest request = ServletFilter.localRequest.get();
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        Employee manager = employeeService.findEmployeeById(employee.getManagerId());
        delegateTask.setAssignee(manager.getName());

    }
}
