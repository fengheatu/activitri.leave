package com.river.activiti.service.impl;

import com.river.activiti.dao.mapper.EmployeeMapper;
import com.river.activiti.model.pojo.Employee;
import com.river.activiti.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;
import java.util.List;

/**
 * create by river  2017/11/12
 * desc:
 */

@Service()
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;


    @Override
    public Employee findEmployeeById(Long id) {
        return employeeMapper.findEmployeeById(id);
    }

    @Override
    public List<String> findEmployeeByRole(String role) {
        return employeeMapper.findEmployeeByRole(role);
    }
}
