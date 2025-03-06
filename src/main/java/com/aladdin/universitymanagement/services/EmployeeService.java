package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;

import java.util.List;

public interface EmployeeService {

    ResponseEmployeeDto newEmployee(Employee employee);

    ResponseEmployeeDto updateEmployee(Employee employee);

    List<ResponseEmployeeDto> getEmployee(Long id,String name);

    List<ResponseEmployeeDto> getEmployees();

    void deleteEmployeeById(Long id);

    void deleteEmployees();
}
