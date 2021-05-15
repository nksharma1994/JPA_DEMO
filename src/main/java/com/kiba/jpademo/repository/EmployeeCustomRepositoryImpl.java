package com.kiba.jpademo.repository;

import com.kiba.jpademo.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<EmployeeEntity> search(String name, String email, UUID departmentId, String departmentName) {
        CriteriaBuilder employeeCB = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> employeeEntityCriteriaQuery = employeeCB.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> taskEntity = employeeEntityCriteriaQuery.from(EmployeeEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if(StringUtils.hasText(name)) {
            predicates.add(employeeCB.like(taskEntity.get("name"), name));
        }

        if(StringUtils.hasText(email)) {
            predicates.add(employeeCB.like(taskEntity.get("email"), email));
        }

        if(departmentId!=null) {
            predicates.add(employeeCB.equal(taskEntity.get("department").get("id"), departmentId));
        }

        if(StringUtils.hasText(departmentName)) {
            predicates.add(employeeCB.equal(taskEntity.get("department").get("name"), departmentName));
        }

        employeeEntityCriteriaQuery.where(employeeCB.and(predicates.toArray( new Predicate[predicates.size()])));
        return entityManager.createQuery(employeeEntityCriteriaQuery)
                .getResultList();
    }
}
