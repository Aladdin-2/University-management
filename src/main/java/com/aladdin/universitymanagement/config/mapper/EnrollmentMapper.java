package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.Enrollment;
import com.aladdin.universitymanagement.model.dto.enrollment.ResponseEnrollmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;


    public ResponseEnrollmentDto toDto(Enrollment enrollment) {
        return new ResponseEnrollmentDto(
                studentMapper.toDto(enrollment.getStudent()),
                teacherMapper.toDto(enrollment.getTeacher()),
                enrollment.getGrade(),
                enrollment.getEnrollmentDate()
        );
    }

    public List<ResponseEnrollmentDto> toDto(List<Enrollment> enrollments) {
        return enrollments.stream().map(this::toDto).toList();
    }
}
