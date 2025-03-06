package com.aladdin.universitymanagement.model.dto.teacher;

import com.aladdin.universitymanagement.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTeacherDto {

    private String teacherName;
    private Specialty speciality;
}
