package com.aladdin.universitymanagement.model.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseStudentDto {

    private String studentName;
    private Integer course;
}
