package com.river.activiti.service;

import com.river.activiti.model.pojo.Employee;

import java.util.List;

/**
 * create by river  2017/11/12
 * desc:
 */
public interface EmployeeService {
    Employee findEmployeeById(Long id);

    List<String> findEmployeeByRole(String taskDefinitionKey);
}
