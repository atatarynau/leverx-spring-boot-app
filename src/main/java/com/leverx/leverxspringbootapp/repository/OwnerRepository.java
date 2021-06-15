package com.leverx.leverxspringbootapp.repository;

import com.leverx.leverxspringbootapp.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByPassportNumber(String passportNumber);
    Optional<Owner> findByPassportNumber(String passportNumber);
}
