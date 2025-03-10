package com.aladdin.universitymanagement.controllers;

import com.aladdin.universitymanagement.dao.entitys.University;
import com.aladdin.universitymanagement.model.dto.university.ResponseUniversityDto;
import com.aladdin.universitymanagement.services.impl.UniversityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "com.aladdin/university-site/university")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityServiceImpl universityServiceImpl;

    @PostMapping(path = "/new")
    public ResponseUniversityDto newUniversity(@RequestBody University university) {
        return universityServiceImpl.newUniversity(university);
    }

    @PostMapping(path = "/add-employee/{uniId}/uni/{employeeId}/employee")
    public ResponseUniversityDto addEmployeeToUniversity(@PathVariable(name = "uniId") Long uniId,
                                                         @PathVariable(name = "employeeId") Long employeeId) {
        return universityServiceImpl.addEmployeeToUniversity(uniId, employeeId);
    }

    @PostMapping(path = "/add-employees/{uniId}/uni")
    public ResponseUniversityDto addEmployeesToUniversity(
            @PathVariable Long uniId,
            @RequestBody Long... employeeIds) {
        return universityServiceImpl.addEmployeesToUniversity(uniId, employeeIds);
    }


    @PostMapping(path = "/add-enrollment/{uniId}/uni/{enrollmentId}/enrollment")
    public ResponseUniversityDto addEnrollmentToUniversity(@PathVariable Long uniId,
                                                           @PathVariable Long enrollmentId) {
        return universityServiceImpl.addEnrollmentToUniversity(uniId, enrollmentId);
    }

    @PostMapping(path = "/add-enrollment/{uniId}/uni")
    public ResponseUniversityDto addEnrollmentsToUniversity(@PathVariable Long uniId,
                                                            @RequestBody Long... enrollmentId) {
        return universityServiceImpl.addEnrollmentsToUniversity(uniId, enrollmentId);
    }

    @PostMapping(path = "add-employee-enrollment/{uniId}/uni/{employeeId}/employee/{enrollmentId}/enrollment")
    public ResponseUniversityDto addEnrollmentAndEmployeeToUniversity(@PathVariable Long uniId,
                                                                      @PathVariable Long employeeId,
                                                                      @PathVariable Long enrollmentId) {
        return universityServiceImpl.addEnrollmentAndEmployeeToUniversity(uniId, employeeId, enrollmentId);
    }

    @GetMapping(path = "get/{uniId}/uni")
    public ResponseUniversityDto getUniversity(@PathVariable Long uniId) {
        return universityServiceImpl.getUniversity(uniId);
    }

    @GetMapping(path = "get")
    public List<ResponseUniversityDto> getUniversities() {
        return universityServiceImpl.getUniversities();
    }


    @DeleteMapping(path = "delete/{uniId}/uni")
    void deleteUniversity(@PathVariable Long uniId) {
        universityServiceImpl.deleteUniversity(uniId);
    }

    @DeleteMapping(path = "delete")
    void deleteUniversities() {
        universityServiceImpl.deleteUniversities();
    }

}
