package com.aladdin.universitymanagement.dao.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "students")
@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends User {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course")
    private Integer course;

    @Transient
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Enrollment> enrollments = new ArrayList<>();

//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "university_id")
//    private University university;
}
