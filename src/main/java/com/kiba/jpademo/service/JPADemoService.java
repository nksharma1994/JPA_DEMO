package com.kiba.jpademo.service;

import com.kiba.jpademo.entity.DepartmentEntity;
import com.kiba.jpademo.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface JPADemoService {
    List<DepartmentEntity> getDepartments();

    List<EmployeeEntity> getEmployees(String name, String email, UUID departmentId, String departmentName);

    List<EmployeeEntity> getEmployees(UUID departmentId);

    List<EmployeeEntity> getEmployees(String departmentName);

    DepartmentEntity saveDepartment(DepartmentEntity departmentEntity);

    DepartmentEntity updateDepartment(DepartmentEntity departmentEntity);
}
