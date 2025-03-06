package com.aladdin.universitymanagement.model.dto.university;

import com.aladdin.universitymanagement.model.dto.enrollment.ResponseEnrollmentDto;
import com.aladdin.universitymanagement.model.dto.employee.ResponseEmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseUniversityDto {

    private String universityName;
    private List<ResponseEnrollmentDto> enrollments;
    private List<ResponseEmployeeDto> employees;

}
