package com.kiba.jpademo.service;

import com.kiba.jpademo.entity.DepartmentEntity;
import com.kiba.jpademo.entity.EmployeeEntity;
import com.kiba.jpademo.repository.DepartmentRepository;
import com.kiba.jpademo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JPADemoServiceImpl implements JPADemoService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public JPADemoServiceImpl(DepartmentRepository departmentRepository,
                              EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository =employeeRepository;
    }
    @Override
    public List<DepartmentEntity> getDepartments() {
        return StreamSupport
                .stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeEntity> getEmployees(String name, String email, UUID departmentId, String departmentName) {
        return employeeRepository.search(name, email, departmentId, departmentName);
    }

    @Override
    public List<EmployeeEntity> getEmployees(UUID departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    @Override
    public List<EmployeeEntity> getEmployees(String departmentName) {
        return employeeRepository.findAllByDepartmentName(departmentName);
    }

    @Override
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    @Override
    public DepartmentEntity updateDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity departmentEntityFromDB = departmentRepository
                .findById(departmentEntity.getId()).orElse(null);
        if (departmentEntityFromDB!=null) {
            departmentEntityFromDB.setDummy(departmentEntity.getDummy());
            return departmentRepository.save(departmentEntityFromDB);
        }
        return null;
    }
}
