package com.aladdin.universitymanagement.model.dto.teacher;

import com.aladdin.universitymanagement.model.dto.student.ResponseStudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherWithStudentsDto {

    private String teacherName;
    private List<ResponseStudentDto> students = new ArrayList<>();
}
