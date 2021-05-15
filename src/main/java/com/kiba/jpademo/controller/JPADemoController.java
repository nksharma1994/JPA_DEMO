package com.kiba.jpademo.controller;

import com.kiba.jpademo.entity.DepartmentEntity;
import com.kiba.jpademo.entity.EmployeeEntity;
import com.kiba.jpademo.service.JPADemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "kiba")
public class JPADemoController {
    private final JPADemoService demoService;

    @Autowired
    public JPADemoController(JPADemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("departments")
    public ResponseEntity<List<DepartmentEntity>> getDepartments() {
        return ResponseEntity.ok(demoService.getDepartments());
    }

    @GetMapping("employees")
    public ResponseEntity<List<EmployeeEntity>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UUID departmentId,
            @RequestParam(required = false) String departmentName
    ) {
        return ResponseEntity.ok(demoService.getEmployees(name, email, departmentId, departmentName));
    }

    @GetMapping("employees/departmentId")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByDepartmentId(
            @RequestParam UUID departmentId
    ) {
        return ResponseEntity.ok(demoService.getEmployees(departmentId));
    }

    @GetMapping("employees/departmentName")
    public ResponseEntity<List<EmployeeEntity>> getEmployeesByDepartmentName(
            @RequestParam String departmentName
    ) {
        return ResponseEntity.ok(demoService.getEmployees(departmentName));
    }

    @PostMapping("department")
    public ResponseEntity<DepartmentEntity> saveDepartment(
            @RequestBody DepartmentEntity departmentEntity
    ) {
        return ResponseEntity.ok(demoService.saveDepartment(departmentEntity));
    }

    @PutMapping("department")
    public ResponseEntity<DepartmentEntity> updateDepartment(
            @RequestBody DepartmentEntity departmentEntity
    ) {
        return ResponseEntity.ok(demoService.updateDepartment(departmentEntity));
    }
}
