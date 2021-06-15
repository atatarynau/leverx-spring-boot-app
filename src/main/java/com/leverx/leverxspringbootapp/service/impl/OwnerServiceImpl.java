package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.dto.OwnerSaveDto;
import com.leverx.leverxspringbootapp.exception.EntityAlreadyExist;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.repository.OwnerRepository;
import com.leverx.leverxspringbootapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        String passportNumber = owner.getPassportNumber();
        if(!ownerRepository.existsByPassportNumber(passportNumber)){
            Owner ownerFromDb = ownerRepository.save(owner);
            return ownerFromDb;
        }else {
            throw new EntityAlreadyExist("Owner with passport number '"+passportNumber+"' already exist!");
        }
    }

    @Override
    public Owner getById(long id) {
//        Owner owner = ownerRepository.findById(id).orElseThrow(() -> {
//            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
//        });
        Optional<Owner> byId = ownerRepository.findById(id);
        Owner owner;
        if(byId.isPresent()) {
            owner = byId.get();
        }else {
            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
        }
        return owner;
    }

    @Override
    public Owner getByPassportNumber(String passportNumber) {
//        Owner owner = ownerRepository.findByPassportNumber(passportNumber).orElseThrow(() -> {
//            throw new EntityDoesntExist("Owner with passport number '" + passportNumber + "' doesn't exist");
//        });

        Optional<Owner> byId = ownerRepository.findByPassportNumber(passportNumber);
        Owner owner;
        if(byId.isPresent()) {
            owner = byId.get();
        }else {
            throw new EntityDoesntExist("Owner with passport number '" + passportNumber + "' doesn't exist");
        }
        return owner;
    }

    @Override
    public boolean isPassportNumberExist(String passportNumber) {
        boolean result = ownerRepository.existsByPassportNumber(passportNumber);
        return result;
    }

    @Override
    public boolean addPetById(Pet pet, long id) {
        Optional<Owner> byId = ownerRepository.findById(id);
//        Owner owner = byId.orElseThrow(() -> {
//            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
//        });
        Owner owner;

        if(byId.isPresent()){
            owner = byId.get();
        }else{
            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
        }
        Set<Pet> pets = owner.getPets();
        boolean result = pets.add(pet);
        return result;
    }

    @Override
    public Owner toEntity(OwnerSaveDto ownerSaveDto) {
        String passportNumber = ownerSaveDto.getPassportNumber();
        String firstName = ownerSaveDto.getFirstName();
        String lastName = ownerSaveDto.getLastName();
        String phoneNumber = ownerSaveDto.getPhoneNumber();

        Owner owner = new Owner();
        owner.setPhoneNumber(phoneNumber);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setPassportNumber(passportNumber);
        return owner;
    }

    @Override
    public void deleteById(long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        }else {
            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
        }
    }
}
