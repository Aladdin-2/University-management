package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.services.impl.EmployeeServiceImpl;
import com.aladdin.universitymanagement.services.impl.StudentServiceImpl;
import com.aladdin.universitymanagement.services.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "com.aladdin/university-site/register")
@RequiredArgsConstructor
public class RegistrationController {


    private final EmployeeServiceImpl employeeServiceImpl;
    private final StudentServiceImpl studentServiceImpl;
    private final TeacherServiceImpl teacherServiceImpl;


    @PostMapping(path = "/employee")
    public ResponseEmployeeDto newEmployee(@RequestBody Employee employee) {
        return employeeServiceImpl.newEmployee(employee);
    }

    @PostMapping(path = "/student")
    public ResponseStudentDto newEmployee(@RequestBody Student student) {
        return studentServiceImpl.newStudent(student);
    }

    @PostMapping(path = "/teacher")
    public ResponseTeacherDto newEmployee(@RequestBody Teacher teacher) {
        return teacherServiceImpl.newTeacher(teacher);
    }


}
