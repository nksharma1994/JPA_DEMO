package com.kiba.jpademo.repository;

import com.kiba.jpademo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID>, EmployeeCustomRepository {
    //@Query("select ee from EmployeeEntity ee where name like :name")
    List<EmployeeEntity> findAllByNameLike(String name);
    //@Query("select ee from EmployeeEntity ee join ee.department where ee.department.id =:departmentId")
    @Query("select ee from EmployeeEntity ee where ee.department.id =:departmentId")
    List<EmployeeEntity> findAllByDepartmentId(UUID departmentId);
    //@Query("select ee from EmployeeEntity ee where ee.department.name =:departmentName")
    @Query("select ee from EmployeeEntity ee join ee.department where ee.department.name =:departmentName")
    List<EmployeeEntity> findAllByDepartmentName(String departmentName);
}
