package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Pet;
import java.util.List;

public interface PetService {

    Pet getById(long id);

    void delete(long id);

    List<Pet> getAllPets();
}
