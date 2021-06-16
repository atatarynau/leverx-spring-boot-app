package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.dto.OwnerSaveDto;
import com.leverx.leverxspringbootapp.exception.EntityAlreadyExist;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.repository.OwnerRepository;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CatService catService;

    @Override
    public Owner save(Owner owner) {
        log.info("Save owner '"+owner+"'");
        String passportNumber = owner.getPassportNumber();
        if(!ownerRepository.existsByPassportNumber(passportNumber)){
            log.info("Owner with passport number '"+passportNumber+"' doesn't exist.");
            Owner ownerFromDb = ownerRepository.save(owner);
            log.info("Owner '"+ownerFromDb+"' was saved.");
            return ownerFromDb;
        }else {
            log.warn("Owner with passport number '"+passportNumber+"' already exist!");
            throw new EntityAlreadyExist("Owner with passport number '"+passportNumber+"' already exist!");
        }
    }

    @Override
    public Owner getById(long id) {
        log.info("Get owner by id '"+id+"'");
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> {
            log.warn("Owner with id '" + id + "' doesn't exist.");
            return new EntityDoesntExist("Owner with id '" + id + "' doesn't exist.");
        });
        log.info("Owner with id '"+id+"' was found.");
        return owner;
    }

    @Override
    public Owner toEntity(OwnerSaveDto ownerSaveDto) {
        log.info("Cat dto '"+ownerSaveDto+"' to cat.");
        String passportNumber = ownerSaveDto.getPassportNumber();
        String firstName = ownerSaveDto.getFirstName();
        String lastName = ownerSaveDto.getLastName();
        String phoneNumber = ownerSaveDto.getPhoneNumber();

        Owner owner = new Owner();
        owner.setPhoneNumber(phoneNumber);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setPassportNumber(passportNumber);
        log.info("Owner dto was transferred.");
        return owner;
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete owner by id '"+id+"'");
        if (ownerRepository.existsById(id)) {
            log.info("Owner with id '"+id+"' exists.");
            ownerRepository.deleteById(id);
            log.info("Owner with id '"+id+"' was deleted.");
        }else {
            log.warn("Owner with id '"+id+"' doesn't exist.");
            throw new EntityDoesntExist("Owner with id '" + id + "' doesn't exist.");
        }
    }

    @Override
    public void killOwnerById(long id) {
        log.info("Kill owner by id '"+id+"'");
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> {
            log.warn("Owner with id '" + id + "' doesn't exist");
            return new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
        });
        owner.setAlive(false);
        Set<Pet> pets = owner.getPets();
        for (Pet pet : pets) {
            pet.setAlive(false);
        }
        ownerRepository.save(owner);
        log.info("Owner with id '"+id+"' was killed.");
    }

    @Override
    public boolean isAliveById(long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> {
            log.warn("Owner with id '" + id + "' doesn't exist");
            return new EntityDoesntExist("Owner with id '" + id + "' doesn't exist");
        });
        boolean isAlive = owner.isAlive();
        log.info("Owner is alive: "+isAlive);
        return isAlive;
    }
}
