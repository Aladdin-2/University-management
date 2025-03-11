package com.aladdin.universitymanagement.model.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseEmployeeDto {
    private String employeeName;
    private String job;
}
