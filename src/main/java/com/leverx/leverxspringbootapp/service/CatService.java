package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Cat;

public interface CatService {

    Cat save(Cat cat, long ownerId);

    Cat getById(long id);

    void deleteById(long id);

    void update(Cat cat);

    boolean isAliveById(long id);
}
