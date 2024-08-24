package com.web.desenvolvimento.edusphere.repositories;

import com.web.desenvolvimento.edusphere.domain.allocation.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAllocationRepository extends JpaRepository<Allocation, Long> {
}
