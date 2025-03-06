package com.aladdin.universitymanagement.dao.entitys;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "university")
public class University {

    @Id
    @Column(name = "university_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Enrollment> enrollments = new ArrayList<>();

//    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Student> students = new ArrayList<>();
//
//    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<Teacher> teachers = new ArrayList<>();

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();
}
