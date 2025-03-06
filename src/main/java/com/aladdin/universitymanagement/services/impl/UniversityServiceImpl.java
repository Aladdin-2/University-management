package com.aladdin.universitymanagement.services.impl;

import com.aladdin.universitymanagement.config.mapper.UniversityMapper;
import com.aladdin.universitymanagement.dao.entitys.Employee;
import com.aladdin.universitymanagement.dao.entitys.Enrollment;
import com.aladdin.universitymanagement.dao.entitys.University;
import com.aladdin.universitymanagement.dao.repositorys.EmployeeRepository;
import com.aladdin.universitymanagement.dao.repositorys.EnrollmentRepository;
import com.aladdin.universitymanagement.dao.repositorys.UniversityRepository;
import com.aladdin.universitymanagement.model.dto.university.ResponseUniversityDto;
import com.aladdin.universitymanagement.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {


    private final UniversityRepository universityRepository;
    private final UniversityMapper universityMapper;
    private final EmployeeRepository employeeRepository;
    private final EnrollmentRepository enrollmentRepository;


    @Override
    public ResponseUniversityDto newUniversity(University university) {
        universityRepository.save(university);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto addEmployeeToUniversity(Long uniId, Long employeeId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));

        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new RuntimeException("No employee found with this id!"));

        employee.setUniversity(university);
        employeeRepository.save(employee);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto addEmployeesToUniversity(Long uniId, Long... employeeId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));

        List<Employee> allById = employeeRepository
                .findAllById(Arrays.asList(employeeId));

        allById.forEach(employee -> employee.setUniversity(university));
        employeeRepository.saveAll(allById);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto addEnrollmentToUniversity(Long uniId, Long enrollmentId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));

        Enrollment enrollment = enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("No enrollment found with this id"));

        enrollment.setUniversity(university);
        enrollmentRepository.save(enrollment);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto addEnrollmentsToUniversity(Long uniId, Long... enrollmentId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));

        List<Enrollment> allById = enrollmentRepository
                .findAllById(Arrays.asList(enrollmentId));

        allById.forEach(enrollment -> enrollment.setUniversity(university));
        enrollmentRepository.saveAll(allById);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto addEnrollmentAndEmployeeToUniversity(Long uniId, Long employeeId, Long enrollmentId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));

        Employee employee = employeeRepository
                .findById(employeeId)
                .orElseThrow(() -> new RuntimeException("No employee found with this id!"));

        Enrollment enrollment = enrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("No enrollment found with this id"));

        employee.setUniversity(university);
        enrollment.setUniversity(university);

        enrollmentRepository.save(enrollment);
        employeeRepository.save(employee);
        return universityMapper.toDto(university);
    }

    @Override
    public ResponseUniversityDto getUniversity(Long uniId) {
        University university = universityRepository
                .findById(uniId)
                .orElseThrow(() -> new RuntimeException("No university found with this id!"));
        return universityMapper.toDto(university);
    }

    @Override
    public List<ResponseUniversityDto> getUniversities() {
        List<University> all = universityRepository.findAll();
        return universityMapper.toDto(all);
    }

    @Override
    public void deleteUniversity(Long uniId) {
        universityRepository.deleteById(uniId);
    }

    @Override
    public void deleteUniversities() {
        universityRepository.deleteAll();
        universityRepository.resetAutoIncrement();
    }
}
