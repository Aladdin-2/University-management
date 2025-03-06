package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.model.dto.student.StudentWithTeachersDto;
import com.aladdin.universitymanagement.model.dto.teacher.TeacherWithStudentsDto;
import com.aladdin.universitymanagement.services.impl.EnrollmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "com.aladdin/university-site/enrollment/")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentServiceImpl enrollmentServiceImpl;

    @PostMapping("/teacher")
    public TeacherWithStudentsDto addStudentToTeacher(@RequestParam Long teacherId,
                                                      @RequestParam Long studentId) {
        return enrollmentServiceImpl.addStudentToTeacher(teacherId, studentId);
    }

    @PostMapping("/student")
    public StudentWithTeachersDto addTeacherToStudent(@RequestParam Long studentId,
                                                      @RequestParam Long teacherId) {
        return enrollmentServiceImpl.addTeacherToStudent(studentId, teacherId);
    }

    @GetMapping(path = "/student/{id}")
    public StudentWithTeachersDto getStudentSTeachers(@PathVariable(name = "id") Long studentId) {
        return enrollmentServiceImpl.getStudentSTeachers(studentId);
    }

    @GetMapping(path = "/teacher/{id}")
    public TeacherWithStudentsDto getTeacherSStudents(@PathVariable(name = "id") Long teacherId) {
        return enrollmentServiceImpl.getTeacherStudents(teacherId);
    }
}