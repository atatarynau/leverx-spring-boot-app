package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.exception.EntityAlreadyExist;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.exception.EntityIsDead;
import com.leverx.leverxspringbootapp.param.OwnerParamExchangePets;
import com.leverx.leverxspringbootapp.repository.OwnerRepository;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Transactional
    @Override
    public Owner save(Owner owner) {

        log.debug(String.format("Try to save owner '%s'", owner));
        String passportNumber = owner.getPassportNumber();
        if (!ownerRepository.existsByPassportNumber(passportNumber)) {
            Owner ownerFromDb = ownerRepository.save(owner);
            return ownerFromDb;
        } else {
            throw new EntityAlreadyExist(String.format("Owner with passport number '%s' already exist!", passportNumber));
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Owner getById(long id) {

        log.debug(String.format("Try to get owner by id '%s'", id));
        Owner owner = ownerRepository.findById(id).orElseThrow(() ->
                new EntityDoesntExist(String.format("Owner with id '%s' doesn't exist.", id)));
        return owner;
    }

    @Transactional
    @Override
    public void deleteById(long id) {

        log.debug(String.format("Try to delete owner by id '%s'", id));
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
        } else {
            throw new EntityDoesntExist(String.format("Owner with id '%s' doesn't exist.", id));
        }
    }

    @Transactional
    @Override
    public void killOwnerById(long id) {

        log.debug(String.format("Kill owner by id '%s'", id));
        Owner owner = this.getById(id);
        owner.setAlive(false);
        Set<Pet> pets = owner.getPets();
        for (Pet pet : pets) {
            pet.setAlive(false);
        }
        ownerRepository.save(owner);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAliveById(long id) {

        log.debug(String.format("Check that owner with id '%s' is alive", id));
        Owner owner = this.getById(id);
        boolean isAlive = owner.isAlive();
        return isAlive;
    }

    @Transactional
    @Override
    public void update(Owner owner) {

        long id = owner.getId();
        log.debug(String.format("Update owner with id '%s'", id));
        if(this.isAliveById(id)){
            ownerRepository.save(owner);
        }else {
            throw new EntityIsDead("This owner is dead.");
        }
    }

    @Transactional
    @Override
    public void exchangePets(OwnerParamExchangePets ownerParamExchangePets) {

        long firstOwnerId = ownerParamExchangePets.getFirstOwnerId();
        long secondOwnerId = ownerParamExchangePets.getSecondOwnerId();
        long firstOwnerPetId = ownerParamExchangePets.getFirstOwnerPetId();
        long secondOwnerPetId = ownerParamExchangePets.getSecondOwnerPetId();

        log.debug(String.format("Exchange pets between owner with id '%s' and owner with id '%s'", firstOwnerId, secondOwnerId));

        Owner firstOwner = this.getById(firstOwnerId);
        Owner secondOwner = this.getById(secondOwnerId);

        if (firstOwner.isAlive() && secondOwner.isAlive()) {
            Set<Pet> firstOwnerPets = firstOwner.getPets();
            Set<Pet> secondOwnerPets = secondOwner.getPets();
            Pet firstPetById = this.findPetById(firstOwnerPetId, firstOwnerPets);
            Pet secondPetById = this.findPetById(secondOwnerPetId, secondOwnerPets);

            firstOwnerPets.remove(firstPetById);
            firstOwnerPets.add(secondPetById);
            secondPetById.setOwner(firstOwner);

            secondOwnerPets.remove(secondPetById);
            secondOwnerPets.add(firstPetById);
            firstPetById.setOwner(secondOwner);

            ownerRepository.save(firstOwner);
            ownerRepository.save(secondOwner);
        } else {
            throw new EntityIsDead("Owner is dead.");
        }
    }

    private Pet findPetById(long id, Set<Pet> ownerPets){

        log.debug(String.format("Find pet by id '%s'", id));
        Optional<Pet> petById = ownerPets.stream()
                .filter(pet -> pet.getId() == id)
                .findFirst();
        Pet pet = petById.orElseThrow(() -> new EntityDoesntExist("Owner doesn't have pet with id "+id));
        return pet;
    }

    @Override
    public List<Owner> getAll() {

        log.debug("Get all owners.");
        List<Owner> allOwners = ownerRepository.findAll();
        return allOwners;
    }
}
