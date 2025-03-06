package com.aladdin.universitymanagement.services;

import com.aladdin.universitymanagement.dao.entitys.University;
import com.aladdin.universitymanagement.model.dto.university.ResponseUniversityDto;

import java.util.List;

public interface UniversityService {

    ResponseUniversityDto newUniversity(University university);

    ResponseUniversityDto addEmployeeToUniversity(Long uniId, Long employeeId);

    ResponseUniversityDto addEmployeesToUniversity(Long uniId, Long... employeeId);

    ResponseUniversityDto addEnrollmentToUniversity(Long uniId, Long enrollmentId);

    ResponseUniversityDto addEnrollmentsToUniversity(Long uniId, Long... enrollmentId);

    ResponseUniversityDto addEnrollmentAndEmployeeToUniversity(Long uniId, Long employeeId, Long enrollmentId);

    ResponseUniversityDto getUniversity(Long uniId);

    List<ResponseUniversityDto> getUniversities();

    void deleteUniversity(Long uniId);

    void deleteUniversities();
}
