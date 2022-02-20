package com.microservice.department.controller;

import com.microservice.department.entity.Department;
import com.microservice.department.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/departments")
@Slf4j

public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public Department saveDepartment(@Valid @RequestBody Department department) {
        log.info("I m in save department service");
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/{id}")
    public Department findDepartmentById(@PathVariable("id") Long departmentId) {
        log.info("I m in save department service");
        return departmentService.findDepartmentById(departmentId);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Department> getById(@PathVariable("id") Long id){
        Optional<Department> one = departmentService.findById(id);
        return new ResponseEntity<Department>(one.get(), HttpStatus.OK);
    }
}
