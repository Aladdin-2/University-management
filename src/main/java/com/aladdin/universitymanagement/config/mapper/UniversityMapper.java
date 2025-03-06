package com.aladdin.universitymanagement.config.mapper;

import com.aladdin.universitymanagement.dao.entitys.University;
import com.aladdin.universitymanagement.model.dto.university.ResponseUniversityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UniversityMapper {

    private final EnrollmentMapper enrollmentMapper;
    private final EmployeeMapper employeeMapper;


    public ResponseUniversityDto toDto(University university) {
        return new ResponseUniversityDto(
                university.getName(),
                enrollmentMapper.toDto(university.getEnrollments()),
                employeeMapper.toDto(university.getEmployees())
        );
    }

    public List<ResponseUniversityDto> toDto(List<University> universities) {
        return universities.stream().map(this::toDto).toList();
    }

}
