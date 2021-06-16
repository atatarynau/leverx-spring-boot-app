package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Owner;

public interface OwnerService {

    Owner save(Owner owner);

    Owner getById(long id);

    void deleteById(long id);

    void killOwnerById(long id);

    boolean isAliveById(long id);

    void update(Owner owner);
}
