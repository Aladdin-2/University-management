package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByUsername(String name);

    List<Student> findByCourse(Integer course);

    @EntityGraph(attributePaths = {"enrolments.teacher"})
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudentsWithTeachers();


    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE students AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();

}
