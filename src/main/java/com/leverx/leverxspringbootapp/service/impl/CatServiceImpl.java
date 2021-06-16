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
        log.info("Save cat '"+cat+"' by owner id '"+ownerId+"'");
        if(ownerService.isAliveById(ownerId)){
            log.info("Owner is alive. Cat can be saved.");
            Owner owner = ownerService.getById(ownerId);
            cat.setOwner(owner);
            Cat catFromDb = catRepository.save(cat);
            log.info("Cat '"+catFromDb+"' was saved.");
            return catFromDb;
        }
        log.warn("Owner is dead.");
        throw new EntityIsDead("Owner with id '"+ownerId+"' is dead.");
    }

    @Override
    public Cat getById(long id){
        log.info("Find cat by id '"+id+"'");
        Cat cat = catRepository.findById(id).orElseThrow(() -> {
            log.warn("Cat with id '" + id + "' doesn't exist.");
            return new EntityDoesntExist("Cat with id '" + id + "' doesn't exist.");
        });
        log.info("Cat '"+cat+"' was found.");
        return cat;
    }

    @Override
    public Cat toEntity(CatSaveDto catSaveDto) {
        log.info("Cat dto '"+catSaveDto+"' to cat.");
        String age = catSaveDto.getAge();
        String breed = catSaveDto.getBreed();
        String name = catSaveDto.getName();
        boolean hasWool = catSaveDto.isHasWool();
        Cat cat = new Cat();
        cat.setHasWool(hasWool);
        cat.setAge(age);
        cat.setBreed(breed);
        cat.setName(name);
        log.info("Cat dto was transferred.");
        return cat;
    }

    @Override
    public void deleteById(long id) {
        log.info("Delete cat by id '"+id+"'");
        if (catRepository.existsById(id)) {
            log.info("Cat with id '"+id+"' exists.");
            catRepository.deleteById(id);
            log.info("Cat with id '"+id+"' was deleted.");
        }else {
            log.info("Cat with id '"+id+"' doesn't exist.");
            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
        }
    }
}
