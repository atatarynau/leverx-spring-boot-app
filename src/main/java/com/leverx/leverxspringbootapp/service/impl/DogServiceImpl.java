package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.DogRepository;
import com.leverx.leverxspringbootapp.service.DogService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

    private final OwnerService ownerService;
    private final DogRepository dogRepository;

    @Transactional
    @Override
    public Dog save(Dog dog, long ownerId) {

        log.debug(String.format("Try to save dog '%s' by owner id '%s'", dog, ownerId));
        if (ownerService.isAliveById(ownerId)) {
            Owner owner = ownerService.getById(ownerId);
            dog.setOwner(owner);
            Dog dogFromDb = dogRepository.save(dog);
            return dogFromDb;
        }
        throw new EntityIsDead(String.format("Owner with id '%s' is dead.", ownerId));
    }

    @Transactional(readOnly = true)
    @Override
    public Dog getById(long id) {

        log.debug(String.format("Try to find dog by id '%s'", id));
        Dog dog = dogRepository.findById(id).orElseThrow(() ->
                new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist.", id)));
        return dog;
    }

    @Transactional
    @Override
    public void deleteById(long id) {

        log.debug(String.format("Try to delete dog by id '%s'", id));
        if (dogRepository.existsById(id)) {
            dogRepository.deleteById(id);
        } else {
            throw new EntityDoesntExist(String.format("Dog with id '%s' doesn't exist", id));
        }
    }

    @Transactional
    @Override
    public void update(Dog dog) {

        long id = dog.getId();
        log.debug(String.format("Try to update dog by id '%s'", id));
        Dog dogFromBd = dogRepository.findById(id).orElseThrow(()-> new EntityDoesntExist(String.format("Dog with id " +
                "'%s' doesn't exist", id)));
        if (dogFromBd.isAlive()) {
            dogRepository.save(dog);
        } else throw new EntityDoesntExist(String.format("Dog with id '%s' is dead", id));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAliveById(long id) {

        log.debug(String.format("Check that dog with id '%s' is alive", id));
        Dog dog = this.getById(id);
        boolean isAlive = dog.isAlive();
        return isAlive;
    }
}