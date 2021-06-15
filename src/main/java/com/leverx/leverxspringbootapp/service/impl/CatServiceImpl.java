package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.dto.CatSaveDto;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.CatRepository;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private OwnerService ownerService;

    @Override
    public Cat save(Cat cat, long ownerId) {
        if(ownerService.isAliveById(ownerId)){
            Owner owner = ownerService.getById(ownerId);
            cat.setOwner(owner);
            Cat catFromDb = catRepository.save(cat);
            return catFromDb;
        }
        throw new EntityIsDead("Owner with id '"+ownerId+"' is dead");
    }

    @Override
    public Cat getById(long id) {

//        Optional<Cat> byId = catRepository.findById(id);
//        Cat cat = byId.orElseThrow(() -> {
//            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
//        });
        Optional<Cat> byId = catRepository.findById(id);
        Cat cat;
        if(byId.isPresent()){
            cat = byId.get();
        }else {
            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
        }

        return cat;
    }

    @Override
    public Cat toEntity(CatSaveDto catSaveDto) {
        String age = catSaveDto.getAge();
        String breed = catSaveDto.getBreed();
        String name = catSaveDto.getName();
        boolean hasWool = catSaveDto.isHasWool();
        Cat cat = new Cat();
        cat.setHasWool(hasWool);
        cat.setAge(age);
        cat.setBreed(breed);
        cat.setName(name);
        return cat;
    }

    @Override
    public void deleteById(long id) {
        if (catRepository.existsById(id)) {
            catRepository.deleteById(id);
        }else {
            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
        }
    }

    @Override
    public void killCatById(long id) {
        Optional<Cat> byId = catRepository.findById(id);
        if (byId.isPresent()) {
            Cat cat = byId.get();
            cat.setAlive(false);
            catRepository.save(cat);
        }
    }
}
