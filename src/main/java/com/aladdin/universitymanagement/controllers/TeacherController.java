package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.model.enums.Specialty;
import com.aladdin.universitymanagement.services.impl.TeacherServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "com.aladdin/university-site/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherServiceImpl teacherServiceImpl;

    @PostMapping(path = "new")
    public ResponseTeacherDto newTeacher(@RequestBody Teacher teacher) {
        return teacherServiceImpl.newTeacher(teacher);
    }

    @PostMapping(path = "put")
    ResponseTeacherDto updateTeacher(@RequestBody Teacher teacher) {
        return teacherServiceImpl.updateTeacher(teacher);
    }

    @GetMapping(path = "get")
    public List<ResponseTeacherDto> getTeacher(@RequestParam(name = "id", required = false) Long id,
                                               @RequestParam(name = "name", required = false) String name,
                                               @RequestParam(name = "specialty",required = false) Specialty specialty) {
        return teacherServiceImpl.getTeacher(id, name, specialty);
    }

    @GetMapping(path = "get-all")
    public List<ResponseTeacherDto> getTeachers() {
        return teacherServiceImpl.getTeachers();
    }

    @GetMapping(path = "sort/{type}")
    public List<ResponseTeacherDto> sortedTeacher(@RequestParam(name = "name", required = false) String name,
                                                  @PathVariable(name = "type") String sortType) {
        return teacherServiceImpl.sortedTeacher(name, sortType);
    }

    @DeleteMapping(path = "delete/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherServiceImpl.deleteTeacher(id);
    }

    @DeleteMapping(path = "delete-all")
    void deleteStudents() {
        teacherServiceImpl.deleteTeachers();
    }
}
