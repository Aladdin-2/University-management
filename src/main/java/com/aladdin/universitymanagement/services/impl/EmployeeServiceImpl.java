package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.EmployeeMapper;
import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.dao.entitys.Role;
import com.aladdin.universitymanagement.dao.repositorys.EmployeeRepository;
import com.aladdin.universitymanagement.dao.repositorys.RoleRepository;
import com.aladdin.universitymanagement.email.ConfirmationEmailServiceImpl;
import com.aladdin.universitymanagement.exception.ResourceNotFoundException;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import com.aladdin.universitymanagement.services.EmployeeService;
import com.aladdin.universitymanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ConfirmationEmailServiceImpl emailService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEmployeeDto newEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.getRoles().add(Role.builder()
                .name("EMPLOYEE").build());
        String activationToken = jwtUtil.generateActivationToken(employee.getEmail());
        roleRepository.saveAll(employee.getRoles());
        employeeRepository.save(employee);
        emailService.sendEmail(employee.getEmail(), "Thank you for registering", activationToken);
        return employeeMapper.toDto(employee);
    }

    @Override
    public ResponseEmployeeDto updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository
                .findById(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
        employee1.setUsername(employee.getUsername());
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
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
            employees.addAll(employeeRepository.findByUsername(name));
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
