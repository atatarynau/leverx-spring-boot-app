package com.leverx.leverxspringbootapp.service;


import com.leverx.leverxspringbootapp.dto.OwnerSaveDto;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.entity.Pet;

public interface OwnerService {

    Owner save(Owner owner);
    Owner getById(long id);
    Owner getByPassportNumber(String passportNumber);
    boolean isPassportNumberExist(String passportNumber);
    boolean addPetById(Pet pet, long id);
    Owner toEntity(OwnerSaveDto ownerSaveDto);
    void deleteById(long id);
    void killOwnerById(long id);
    boolean isAliveById(long id);
}
