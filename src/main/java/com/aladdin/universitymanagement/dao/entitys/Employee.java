package com.aladdin.universitymanagement.dao.entitys;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends User{

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job")
    private String job;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "university_id")
    private University university;
    

}
