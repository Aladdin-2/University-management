package com.aladdin.universitymanagement.model.dto.student;

import com.aladdin.universitymanagement.model.dto.teacher.ResponseTeacherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentWithTeachersDto {

    private String studentName;
    private List<ResponseTeacherDto> teachers = new ArrayList<>();
}
