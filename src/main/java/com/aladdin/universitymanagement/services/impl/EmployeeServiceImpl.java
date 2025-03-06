package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.EmployeeMapper;
import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.dao.repositorys.EmployeeRepository;
import com.aladdin.universitymanagement.exception.ResourceNotFoundException;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import com.aladdin.universitymanagement.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public ResponseEmployeeDto newEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    public ResponseEmployeeDto updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository
                .findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
        employee1.setEmployeeName(employee.getEmployeeName());
        employeeRepository.save(employee1);
        return employeeMapper.toDto(employee);
    }

    @Override
    public List<ResponseEmployeeDto> getEmployee(Long id, String name) {
        List<Employee> employees = new ArrayList<>();
        if (id != null) {
            employees.add(employeeRepository
                    .findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found!")));
        }
        if (name != null) {
            employees.addAll(employeeRepository.findByEmployeeName(name));
        }
        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("Not found with these parameters!");
        }
        return employeeMapper.toDto(employees);
    }

    @Override
    public List<ResponseEmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toDto(employees);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteEmployees() {
        employeeRepository.deleteAll();
        employeeRepository.resetAutoIncrement();
    }
}
