package com.microservice.department.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "demo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    @NotNull(message = "please enter name")
    private String departmentName;

    @Column(name = "department_address")
    @NotNull
    private String departmentAddress;

    @Column(name = "department_code")
    @Enumerated(EnumType.STRING)
    private DepartmentCode departmentCode;


}
