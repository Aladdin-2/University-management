package com.aladdin.universitymanagement.dao.repositorys;

import com.aladdin.universitymanagement.dao.entitys.Enrollment;
import com.aladdin.universitymanagement.dao.entitys.Student;
import com.aladdin.universitymanagement.dao.entitys.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @EntityGraph(attributePaths = {"teachers"})
    @Query("SELECT s FROM Student s")
    List<Student> findAllWithTeachers();


    @EntityGraph(attributePaths = {"students"})
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllWithStudents();

    @Query("SELECT t FROM Teacher t JOIN Enrollment e ON t.id = e.teacher.id WHERE e.student.id = :studentId")
    List<Teacher> findTeachersByStudentId(Long studentId);

    @Query("SELECT s FROM Student s JOIN Enrollment e ON s.id= e.student.id WHERE e.teacher.id = :teacherId")
    List<Student> findStudentsByTeacherId(Long teacherId);


    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByTeacherId(Long teacherId);

    boolean existsByTeacherAndStudent(Teacher teacher, Student student);
}
