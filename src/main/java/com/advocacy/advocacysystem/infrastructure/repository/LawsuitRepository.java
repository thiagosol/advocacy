package com.advocacy.advocacysystem.infrastructure.repository;

import com.advocacy.advocacysystem.core.domain.Lawsuit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawsuitRepository extends JpaRepository<Lawsuit, Long> {

    Page<Lawsuit> findByCustomersId(Long customerId, Pageable pageable);

}
