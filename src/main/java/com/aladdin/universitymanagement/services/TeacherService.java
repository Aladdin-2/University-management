package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.model.enums.Specialty;

import java.util.List;

public interface TeacherService {

    ResponseTeacherDto newTeacher(Teacher teacher);

    ResponseTeacherDto updateTeacher(Teacher teacher);

    List<ResponseTeacherDto> getTeacher(Long id, String name, Specialty specialty);

    List<ResponseTeacherDto> getTeachers();

    List<ResponseTeacherDto> sortedTeacher(String name, String sortType);

    void deleteTeacher(Long id);

    void deleteTeachers();
}
