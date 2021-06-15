package com.leverx.leverxspringbootapp.repository;

import com.leverx.leverxspringbootapp.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
