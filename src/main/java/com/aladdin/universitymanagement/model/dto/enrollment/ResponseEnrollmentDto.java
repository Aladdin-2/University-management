package com.aladdin.universitymanagement.model.dto.enrollment;

import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseEnrollmentDto {

    private ResponseStudentDto student;

    private ResponseTeacherDto teacher;

    private String grade;

    private LocalDate enrollmentDate;

}
