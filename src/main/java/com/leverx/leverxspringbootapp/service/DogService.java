package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.dto.DogSaveDto;
import com.leverx.leverxspringbootapp.entity.Dog;

public interface DogService {

    Dog save(Dog dog, long ownerId);
    Dog getById(long id);
    Dog toEntity(DogSaveDto dogSaveDto);
    void deleteById(long id);
}
