package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Dog;

public interface DogService {

    Dog save(Dog dog, long ownerId);

    Dog getById(long id);

    void deleteById(long id);

    void update(Dog dog);
}
