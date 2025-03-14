package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.StudentMapper;
import com.aladdin.universitymanagement.dao.entitys.Role;
import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.dao.repositorys.StudentRepository;
import com.aladdin.universitymanagement.email.ConfirmationEmailServiceImpl;
import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import com.aladdin.universitymanagement.services.StudentService;
import com.aladdin.universitymanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationEmailServiceImpl emailService;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseStudentDto newStudent(Student student) {
        student.getRoles().add(Role.builder()
                .name("STUDENT").build());
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        String activationToken  = jwtUtil.generateActivationToken(student.getEmail());
        studentRepository.save(student);
        emailService.sendEmail(student.getEmail(), "Thank you for registering",activationToken );
        return studentMapper.toDto(student);
    }

    @Override
    public ResponseStudentDto updateStudent(Student student) {
        Student student1 = studentRepository.findById(student.getId()).orElse(student);
        student1.setUsername(student.getUsername());
        student1.setCourse(student.getCourse());
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(student1);
        return studentMapper.toDto(student1);
    }

    @Override
    public List<ResponseStudentDto> getStudent(Long id, String name, Integer course) {
        List<Student> students = new ArrayList<>();
        if (id != null) {
            students.add(studentRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found!")));
        }
        if (name != null) {
            students.addAll(studentRepository.findByUsername(name));
        }
        if (course != null) {
            students.addAll(studentRepository.findByCourse(course));
        }
        if (students.isEmpty()) {
            students.addAll(studentRepository.findAll());
        }
        return studentMapper.toDto(students);
    }

    @Override
    public List<ResponseStudentDto> sortedStudent(String sortType, String name) {
        List<Student> students = new ArrayList<>();
        if (name != null) {
            students.addAll(studentRepository.findByUsername(name));
        } else {
            students.addAll(studentRepository.findAll());
        }
        if (students.isEmpty()) {
            throw new RuntimeException("Student DB is empty");
        }
        if (sortType != null) {
            switch (sortType.toLowerCase()) {
                case "course" -> students.sort(Comparator.comparingInt(Student::getCourse));
                case "name" -> students.sort(Comparator.comparing(Student::getUsername));
            }
        }
        return studentMapper.toDto(students);
    }

    @Override
    public List<ResponseStudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDto(students);
    }

    @Override
    public void deleteStudent(Long id) {
        if (id == null) {
            throw new NullPointerException("Id is null");
        }
        studentRepository.deleteById(id);
        log.info("Student successfully deleted!");
    }

    @Override
    public void deleteStudents() {
        studentRepository.deleteAll();
        studentRepository.resetAutoIncrement();
    }
}
