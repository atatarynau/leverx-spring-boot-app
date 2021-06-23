package com.leverx.leverxspringbootapp.repository;

import com.leverx.leverxspringbootapp.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
