package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.dto.DogSaveDto;
import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.DogRepository;
import com.leverx.leverxspringbootapp.service.DogService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class DogServiceImpl implements DogService {

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private DogRepository dogRepository;

    @Override
    public Dog save(Dog dog, long ownerId) {
        log.info("Save dog '"+dog+"' by owner id '"+ownerId+"'");
        if(ownerService.isAliveById(ownerId)){
            log.info("Owner is alive. Dog can be saved.");
            Owner owner = ownerService.getById(ownerId);
            dog.setOwner(owner);
            Dog dogFromDb = dogRepository.save(dog);
            log.info("Dog '"+dogFromDb+"' was saved.");
            return dogFromDb;
        }
        log.warn("Owner is dead.");
        throw new EntityIsDead("Owner with id '"+ownerId+"' is dead.");
    }

    @Override
    public Dog getById(long id){
        log.info("Find dog by id '"+id+"'");
        Dog dog = dogRepository.findById(id).orElseThrow(() -> {
            log.warn("Dog with id '" + id + "' doesn't exist.");
            return new EntityDoesntExist("Dog with id '" + id + "' doesn't exist.");
        });
        log.info("Dog '"+dog+"' was found.");
        return dog;
    }

    @Override
    public Dog toEntity(DogSaveDto dogSaveDto) {
        log.info("Dog dto '"+dogSaveDto+"' to dog.");
        String age = dogSaveDto.getAge();
        String breed = dogSaveDto.getBreed();
        String name = dogSaveDto.getName();
        boolean isTrained = dogSaveDto.isTrained();
        Dog dog = new Dog();
        dog.setTrained(isTrained);
        dog.setAge(age);
        dog.setBreed(breed);
        dog.setName(name);
        log.info("Dog dto was transferred.");
        return dog;
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete dog by id '"+id+"'");
        if (dogRepository.existsById(id)) {
            log.info("Dog with id '"+id+"' exists.");
            dogRepository.deleteById(id);
            log.info("Dog with id '"+id+"' was deleted.");
        }else {
            log.info("Dog with id '"+id+"' doesn't exist.");
            throw new EntityDoesntExist("Dog with id '" + id + "' doesn't exist");
        }
    }
}