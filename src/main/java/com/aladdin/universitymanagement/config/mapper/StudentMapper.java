package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapper {

    public ResponseStudentDto toDto(Student student) {
        return new ResponseStudentDto(student.getUsername(),
                student.getCourse());
    }

    public List<ResponseStudentDto> toDto(List<Student> students) {
        return students.stream().map(this::toDto).toList();
    }
}
