package com.advocacy.advocacysystem.infrastructure.repository;

import com.advocacy.advocacysystem.core.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
