package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByUsername(String name);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE employees AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
