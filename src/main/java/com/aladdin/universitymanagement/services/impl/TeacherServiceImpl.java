package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.TeacherMapper;
import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.dao.repositorys.TeacherRepository;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import com.aladdin.universitymanagement.model.enums.Specialty;
import com.aladdin.universitymanagement.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public ResponseTeacherDto newTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public ResponseTeacherDto updateTeacher(Teacher teacher) {
        Teacher teacher1 = teacherRepository.findById(teacher.getId()).orElseThrow(() -> new RuntimeException("Teacher not found!"));
        teacher1.setTeacherName(teacher.getTeacherName());
        teacher1.setSpeciality(teacher.getSpeciality());
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
            teachers.addAll(teacherRepository.findByTeacherName(name));
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
            teachers.addAll(teacherRepository.findByTeacherName(name));
        }
        if (teachers.isEmpty()) {
            throw new RuntimeException("Teacher not found with this parameters!");
        }
        if (sortType != null) {
            switch (sortType.toLowerCase()) {
                case "name" -> teachers.sort(Comparator.comparing(Teacher::getTeacherName));
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


//        return teacher;
//    public Teacher newTeacherToEnrollment(Long enrollmentId, Long universityId, Teacher teacher) {
//        University university = universityRepository.findById(universityId)
//                .orElseThrow(() -> new RuntimeException("University not found!"));
//        teacher = teacherRepository.save(teacher);
//        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
//        Enrollment enrollment;
//        if (optionalEnrollment.isEmpty()) {
//            enrollment = new Enrollment();
//        } else {
//            enrollment = optionalEnrollment.get();
//
//        }
//        enrollment.setTeacher(teacher);
//        university.getEnrollments().add(enrollment);
//        enrollmentRepository.save(enrollment);
//        universityRepository.save(university);
//    }
}
