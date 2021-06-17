package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.CatRepository;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerService ownerService;

    @Override
    public Cat save(Cat cat, long ownerId) {
        log.info("Save cat '" + cat + "' by owner id '" + ownerId + "'");
        if (ownerService.isAliveById(ownerId)) {
            Owner owner = ownerService.getById(ownerId);
            cat.setOwner(owner);
            Cat catFromDb = catRepository.save(cat);
            log.info("Cat '" + catFromDb + "' was saved.");
            return catFromDb;
        }
        log.warn("Owner is dead.");
        throw new EntityIsDead("Owner with id '" + ownerId + "' is dead.");
    }

    @Override
    public Cat getById(long id) {
        log.info("Find cat by id '" + id + "'");
        Cat cat = catRepository.findById(id).orElseThrow(() -> {
            log.warn("Cat with id '" + id + "' doesn't exist.");
            return new EntityDoesntExist("Cat with id '" + id + "' doesn't exist.");
        });
        log.info("Cat '" + cat + "' was found.");
        return cat;
    }

    @Override
    public void deleteById(long id) {
        if (catRepository.existsById(id)) {
            catRepository.deleteById(id);
            log.info("Cat with id '" + id + "' was deleted.");
        } else {
            log.warn("Cat with id '" + id + "' doesn't exist.");
            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
        }
    }

    @Override
    public void update(Cat cat) {
        long id = cat.getId();
        if (catRepository.existsById(id)) {
            Cat savedCat = catRepository.save(cat);
            log.info("Cat '" + savedCat + "' was update.");
        } else {
            log.debug("Cat with id '" + id + "' doesn't exist");
            throw new EntityDoesntExist("Cat with id '" + id + "' doesn't exist");
        }
    }
}
