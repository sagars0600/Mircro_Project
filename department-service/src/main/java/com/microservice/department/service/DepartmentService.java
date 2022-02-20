package com.microservice.department.service;

import com.microservice.department.entity.Department;
import com.microservice.department.exception.DepartmentNotFoundException;
import com.microservice.department.reposittory.DepartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public Department saveDepartment(Department department) {
        log.info("insdie save department repo");
        return departmentRepo.save(department);
    }

    public Department findDepartmentById(Long departmentId) {

        return departmentRepo.findByDepartmentId(departmentId);
    }

    public Optional<Department> findById(Long id) {
        Optional<Department> user = departmentRepo.findById(id);
        if (!user.isPresent())
            throw new DepartmentNotFoundException("Please Check Deparment ID");
        return user;
    }
}
