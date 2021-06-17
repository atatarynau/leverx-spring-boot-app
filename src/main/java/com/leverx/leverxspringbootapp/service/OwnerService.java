package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.param.OwnerParamExchangePets;

public interface OwnerService {

    Owner save(Owner owner);

    Owner getById(long id);

    void deleteById(long id);

    void killOwnerById(long id);

    boolean isAliveById(long id);

    void update(Owner owner);

    void exchangePets(OwnerParamExchangePets ownerParamExchangePets);
}
