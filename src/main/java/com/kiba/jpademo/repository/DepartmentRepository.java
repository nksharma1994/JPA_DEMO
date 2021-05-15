package com.kiba.jpademo.repository;

import com.kiba.jpademo.entity.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, UUID> {
}
