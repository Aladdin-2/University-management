package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import com.aladdin.universitymanagement.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "com.aladdin/university-site/student/")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PutMapping(path = "put")
    public ResponseStudentDto updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @GetMapping(path = "/get")
    public List<ResponseStudentDto> getStudent(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "name", required = false) String name,
                                               @RequestParam(name = "course", required = false) Integer course) {
        return studentService.getStudent(id, name, course);
    }

    @GetMapping(path = "/get-all")
    public List<ResponseStudentDto> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "sort/{type}")
    public List<ResponseStudentDto> sortedStudent(@PathVariable(name = "type") String sortType,
                                                  @RequestParam(name = "name", required = false) String name) {
        return studentService.sortedStudent(sortType, name);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @DeleteMapping(path = "delete-all")
    public void deleteStudents() {
        studentService.deleteStudents();
    }
}
