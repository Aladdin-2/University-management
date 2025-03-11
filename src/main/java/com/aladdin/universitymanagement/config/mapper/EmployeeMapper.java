package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeMapper {

    public ResponseEmployeeDto toDto(Employee employee) {
        return new ResponseEmployeeDto(
                employee.getUsername(),
                employee.getJob()
        );
    }

    public List<ResponseEmployeeDto> toDto(List<Employee> employees) {
        return employees.stream().map(this::toDto).toList();
    }
}
