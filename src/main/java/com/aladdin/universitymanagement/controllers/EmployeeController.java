package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import com.aladdin.universitymanagement.services.impl.EmployeeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "com.aladdin/university-site/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;


    @PostMapping(path = "/new")
    public ResponseEmployeeDto newEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.newEmployee(employee);
    }

    @PutMapping(path = "/put")
    public ResponseEmployeeDto updateEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.updateEmployee(employee);
    }

    @GetMapping(path = "/get")
    public List<ResponseEmployeeDto> getEmployee(@RequestParam(name = "id", required = false) Long id,
                                                 @RequestParam(name = "name", required = false) String name) {
        return employeeServiceImpl.getEmployee(id, name);
    }

    @GetMapping(path = "get-all")
    public List<ResponseEmployeeDto> getEmployees() {
        return employeeServiceImpl.getEmployees();
    }

    @DeleteMapping(path = "id/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployeeById(id);
    }

    @DeleteMapping(path = "all")
    public void deleteEmployees() {
        employeeServiceImpl.deleteEmployees();
    }

}
