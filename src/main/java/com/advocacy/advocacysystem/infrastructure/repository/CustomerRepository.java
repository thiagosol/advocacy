package com.advocacy.advocacysystem.infrastructure.repository;

import com.advocacy.advocacysystem.core.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}