package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE university AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}
