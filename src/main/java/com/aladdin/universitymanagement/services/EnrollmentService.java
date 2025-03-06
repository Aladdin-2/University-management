package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.model.dto.student.StudentWithTeachersDto;
import com.aladdin.universitymanagement.model.dto.teacher.TeacherWithStudentsDto;

import java.util.List;

public interface EnrollmentService {

    TeacherWithStudentsDto addStudentToTeacher(Long teacherId, Long studentId);

    StudentWithTeachersDto addTeacherToStudent(Long studentId, Long teacherId);

    StudentWithTeachersDto getStudentSTeachers(Long studentId);

    TeacherWithStudentsDto getTeacherStudents(Long teacherId);

    List<StudentWithTeachersDto> getStudentsWithTeacher();

    List<TeacherWithStudentsDto> getTeachersWithStudent();
}
