package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Dog;

public interface DogService {

    Dog save(Dog dog);
    Dog findById(long id);
}
