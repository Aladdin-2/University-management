package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.TeacherMapper;
import com.aladdin.universitymanagement.dao.entitys.Role;
import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.dao.repositorys.TeacherRepository;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.model.enums.Specialty;
import com.aladdin.universitymanagement.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseTeacherDto newTeacher(Teacher teacher) {
        teacher.getRoles().add(Role.builder()
                .name("TEACHER").build());
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public ResponseTeacherDto updateTeacher(Teacher teacher) {
        Teacher teacher1 = teacherRepository.findById(teacher.getId()).orElseThrow(() -> new RuntimeException("Teacher not found!"));
        teacher1.setUsername(teacher.getUsername());
        teacher1.setSpeciality(teacher.getSpeciality());
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher1);
        return teacherMapper.toDto(teacher1);
    }

    @Override
    public List<ResponseTeacherDto> getTeacher(Long id, String name, Specialty specialty) {
        List<Teacher> teachers = new ArrayList<>();
        if (id != null) {
            teachers.add(teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id: " + id)));
        }
        if (name != null) {
            teachers.addAll(teacherRepository.findByUsername(name));
        }
        if (specialty != null) {
            teachers.addAll(teacherRepository.findBySpeciality(specialty));
        }
        if (teachers.isEmpty()) {
            throw new RuntimeException("Not found this parameters!");
        }
        return teacherMapper.toDto(teachers);
    }

    @Override
    public List<ResponseTeacherDto> sortedTeacher(String name, String sortType) {
        List<Teacher> teachers = new ArrayList<>();
        if (name == null) {
            teachers.addAll(teacherRepository.findAll());
        } else {
            teachers.addAll(teacherRepository.findByUsername(name));
        }
        if (teachers.isEmpty()) {
            throw new RuntimeException("Teacher not found with this parameters!");
        }
        if (sortType != null) {
            switch (sortType.toLowerCase()) {
                case "name" -> teachers.sort(Comparator.comparing(Teacher::getUsername));
                case "specialty" -> teachers.sort(Comparator.comparing(Teacher::getSpeciality));
            }
        }
        return teacherMapper.toDto(teachers);
    }

    @Override
    public List<ResponseTeacherDto> getTeachers() {
        return teacherMapper.toDto(teacherRepository.findAll());
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void deleteTeachers() {
        teacherRepository.deleteAll();
        teacherRepository.resetAutoIncrement();
    }

}
