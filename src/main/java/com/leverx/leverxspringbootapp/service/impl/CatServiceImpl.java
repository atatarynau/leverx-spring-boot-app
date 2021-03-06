package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.repository.CatRepository;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerService ownerService;

    @Override
    @Transactional
    public Cat save(Cat cat, long ownerId) {

        log.debug(String.format("Try to save cat '%s' by owner id '%s'", cat, ownerId));
        if (ownerService.isAliveById(ownerId)) {
            Owner owner = ownerService.getById(ownerId);
            cat.setOwner(owner);
            Cat catFromDb = catRepository.save(cat);
            return catFromDb;
        }
        throw new EntityIsDead(String.format("Owner with id '%s' is dead.", ownerId));
    }

    @Override
    @Transactional(readOnly = true)
    public Cat getById(long id) {

        log.debug(String.format("Try to find cat with id '%s'", id));
        Cat cat = catRepository.findById(id).orElseThrow(() ->
                new EntityDoesntExist(String.format("Cat with id '%s' doesn't exist", id)));
        return cat;
    }

    @Override
    @Transactional
    public void deleteById(long id) {

        log.debug(String.format("Try to delete cat with id '%s'", id));
        if (catRepository.existsById(id)) {
            catRepository.deleteById(id);
        } else {
            throw new EntityDoesntExist(String.format("Cat with id '%s' doesn't exist", id));
        }
    }

    @Override
    @Transactional
    public void update(Cat cat) {

        log.debug(String.format("Try to update cat with id '%s'", cat.getId()));
        long id = cat.getId();
        Cat catFromBd = catRepository.findById(id).orElseThrow(()-> new EntityDoesntExist(String.format("Cat with id '%s'" +
                " doesn't exist", id)));
        if (catFromBd.isAlive()) {
            cat.setOwner(catFromBd.getOwner());
            catRepository.save(cat);
        } else {
            throw new EntityDoesntExist(String.format("Cat with id '%s' is dead", id));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isAliveById(long id) {

        log.debug(String.format("Check that cat with id '%s' is alive", id));
        Cat cat = this.getById(id);
        boolean isAlive = cat.isAlive();
        return isAlive;
    }
}
