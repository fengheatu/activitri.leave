package com.river.activiti.service;

import com.river.activiti.model.pojo.Employee;

/**
 * create by river  2017/11/12
 * desc:
 */
public interface EmployeeService {
    Employee findEmployeeById(Long id);
}
