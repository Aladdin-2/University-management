package com.aladdin.universitymanagement.dao.entitys;

import com.aladdin.universitymanagement.model.enums.Specialty;
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
@Table(name = "teachers")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String teacherName;

    @Enumerated(EnumType.STRING)
    private Specialty speciality;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Enrollment> enrollments = new ArrayList<>();
////
//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "university_id")
//    private University university;

}
