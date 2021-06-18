package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.DogRepository;
import com.leverx.leverxspringbootapp.service.DogService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class DogServiceImpl implements DogService {

    private final OwnerService ownerService;
    private final DogRepository dogRepository;

    @Override
    public Dog save(Dog dog, long ownerId) {
        log.info(String.format("Try to save dog '%s' by owner id '%s'", dog, ownerId));
        if (ownerService.isAliveById(ownerId)) {
            Owner owner = ownerService.getById(ownerId);
            dog.setOwner(owner);
            Dog dogFromDb = dogRepository.save(dog);
            return dogFromDb;
        }
        throw new EntityIsDead(String.format("Owner with id '%s' is dead.", ownerId));
    }

    @Override
    public Dog getById(long id) {
        log.info(String.format("Try to find dog by id '%s'", id));
        Dog dog = dogRepository.findById(id).orElseThrow(() ->
                new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist.", id)));
        return dog;
    }

    @Override
    public void deleteById(long id) {
        log.info(String.format("Try to delete dog by id '%s'", id));
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
        } else {
            throw new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist", id));
        }
    }

    @Override
    public void update(Dog dog) {
        long id = dog.getId();
        log.info(String.format("Try to update dog by id '%s'", id));
        if (this.isAliveById(id)) {
            dogRepository.save(dog);
        } else {
            throw new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist", id));
        }
    }

    @Override
    public boolean isAliveById(long id) {
        log.info(String.format("Check that dog with id '%s' is alive", id));
        Dog dog = dogRepository.findById(id).orElseThrow(() ->
                new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist", id)));
        boolean isAlive = dog.isAlive();
        return isAlive;
    }
}