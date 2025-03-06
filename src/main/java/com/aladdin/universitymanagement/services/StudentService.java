package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;

import java.util.List;

public interface StudentService {

    ResponseStudentDto newStudent(Student student);

    ResponseStudentDto updateStudent(Student student);

    List<ResponseStudentDto> sortedStudent(String name, String sortType);

    List<ResponseStudentDto> getStudent(Long id, String name, Integer course);

    List<ResponseStudentDto> getStudents();

    void deleteStudent(Long id);

    void deleteStudents();
}
