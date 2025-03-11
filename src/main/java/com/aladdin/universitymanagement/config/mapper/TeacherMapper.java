package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {

    public ResponseTeacherDto toDto(Teacher teacher) {
        return new ResponseTeacherDto(
                teacher.getUsername(),
                teacher.getSpeciality());
    }

    public List<ResponseTeacherDto> toDto(List<Teacher> teachers) {
        return teachers.stream().map(this::toDto).toList();
    }
}
