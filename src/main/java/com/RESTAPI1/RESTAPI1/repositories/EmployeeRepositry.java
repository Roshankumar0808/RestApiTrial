package com.RESTAPI1.RESTAPI1.repositories;

import com.RESTAPI1.RESTAPI1.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositry extends JpaRepository<EmployeeEntity,Long> {

}
