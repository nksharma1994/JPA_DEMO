package com.kiba.jpademo.repository;

import com.kiba.jpademo.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeCustomRepository {
    List<EmployeeEntity> search(String name, String email, UUID departmentId, String departmentName);
}
