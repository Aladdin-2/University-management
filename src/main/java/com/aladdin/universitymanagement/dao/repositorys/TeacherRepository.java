package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.Teacher;
import com.aladdin.universitymanagement.model.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {


    List<Teacher> findByTeacherName(String name);

    List<Teacher> findBySpeciality(Specialty specialty);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE teachers AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
