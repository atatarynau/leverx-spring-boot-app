package com.leverx.leverxspringbootapp.repository;

import com.leverx.leverxspringbootapp.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
