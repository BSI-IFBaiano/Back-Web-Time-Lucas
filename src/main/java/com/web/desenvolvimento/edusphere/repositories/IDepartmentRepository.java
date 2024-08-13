package com.web.desenvolvimento.edusphere.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.desenvolvimento.edusphere.domain.department.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Long>{
}
