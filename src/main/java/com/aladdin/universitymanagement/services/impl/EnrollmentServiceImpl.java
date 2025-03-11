package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.StudentMapper;
import com.aladdin.universitymanagement.config.mapper.TeacherMapper;
import com.aladdin.universitymanagement.dao.entitys.Enrollment;
import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.dao.repositorys.EnrollmentRepository;
import com.aladdin.universitymanagement.dao.repositorys.StudentRepository;
import com.aladdin.universitymanagement.dao.repositorys.TeacherRepository;
import com.aladdin.universitymanagement.exception.DataAlreadyExistsException;
import com.aladdin.universitymanagement.exception.ResourceNotFoundException;
import com.aladdin.universitymanagement.model.dto.student.StudentWithTeachersDto;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.model.dto.teacher.TeacherWithStudentsDto;
import com.aladdin.universitymanagement.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final StudentMapper studentMapper;

    @Override
    public TeacherWithStudentsDto addStudentToTeacher(Long teacherId, Long studentId) {
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found!"));
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        boolean exists = enrollmentRepository.existsByTeacherAndStudent(teacher, student);
        if (exists) {
            throw new DataAlreadyExistsException("This student is already assigned to this teacher!");
        }
        newEnrollment(teacher, student);
        List<Student> studentsByTeacherId = enrollmentRepository.findStudentsByTeacherId(teacherId);
        return new TeacherWithStudentsDto(
                teacher.getUsername(),
                studentMapper.toDto(studentsByTeacherId)
        );
    }

    @Override
    public StudentWithTeachersDto addTeacherToStudent(Long studentId, Long teacherId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found!"));
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found!"));
        boolean exists = enrollmentRepository.existsByTeacherAndStudent(teacher, student);
        if (exists) {
            throw new DataAlreadyExistsException("This student is already assigned to this teacher!");
        }
        newEnrollment(teacher, student);
        List<Teacher> teachersByStudentId = enrollmentRepository.findTeachersByStudentId(studentId);
        return new StudentWithTeachersDto(
                student.getUsername(),
                teacherMapper.toDto(teachersByStudentId)
        );
    }

    @Override
    public StudentWithTeachersDto getStudentSTeachers(Long studentId) {
        Student student = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        List<Teacher> teachersByStudentId = enrollmentRepository.findTeachersByStudentId(studentId);
        StudentWithTeachersDto responseStudent = new StudentWithTeachersDto();
        responseStudent.setStudentName(student.getUsername());
        responseStudent.setTeachers(teachersByStudentId.isEmpty() ? Collections.emptyList() : teacherMapper.toDto(teachersByStudentId));
        return responseStudent;
    }

    @Override
    public TeacherWithStudentsDto getTeacherStudents(Long teacherId) {
        Teacher teacher = teacherRepository
                .findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found!"));
        List<Student> studentsByTeacherId = enrollmentRepository.findStudentsByTeacherId(teacherId);
        TeacherWithStudentsDto responseTeacher = new TeacherWithStudentsDto();
        responseTeacher.setTeacherName(teacher.getUsername());
        responseTeacher.setStudents(studentsByTeacherId.isEmpty() ? Collections.emptyList() : studentMapper.toDto(studentsByTeacherId));
        return responseTeacher;
    }

    @Override
    public List<StudentWithTeachersDto> getStudentsWithTeacher() {
        List<Student> students = enrollmentRepository.findAllWithTeachers();

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No student has a teacher.");
        }
        return students.stream().map(student -> {
            List<ResponseTeacherDto> teacherDto = teacherMapper.toDto(student.getTeachers());
            return new StudentWithTeachersDto(student.getUsername(),
                    teacherDto);
        }).toList();
    }


    @Override
    public List<TeacherWithStudentsDto> getTeachersWithStudent() {
//        List<Teacher> teachers = enrollmentRepository.findAllWithStudents();
//        if(teachers.isEmpty()){
//            throw new ResourceNotFoundException("No teacher has any students.");
//        }
//
//        return teachers.stream().map(teacher -> {
//            teacher.get
//        })
        return List.of();
    }


    public void newEnrollment(Teacher teacher, Student student) {
        Enrollment enrollment = new Enrollment();
        enrollment.setTeacher(teacher);
        enrollment.setStudent(student);
        teacher.getEnrollments().add(enrollment);
        enrollmentRepository.save(enrollment);
    }
}
