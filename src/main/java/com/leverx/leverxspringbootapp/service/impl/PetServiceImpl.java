package com.leverx.leverxspringbootapp.service.impl;

import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.exception.EntityDoesntExist;
import com.leverx.leverxspringbootapp.repository.PetRepository;
import com.leverx.leverxspringbootapp.service.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    public Pet getById(long id) {

        log.debug(String.format("Try to find pet by id '%s'", id));
        Pet petFromBd = petRepository.findById(id).orElseThrow(() -> new EntityDoesntExist(String.format("Pet with id '%s'" +
                " doesn't exist", id)));
        return petFromBd;
    }

    @Override
    public void delete(long id) {

        log.debug(String.format("Try to delete pet by id '%s'", id));
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id);
        } else {
            throw new EntityDoesntExist(String.format("Pet with id '%s' doesn't exist", id));
        }
    }

    @Override
    public List<Pet> getAllPets() {

        log.debug("Try to find all pets");
        List<Pet> allPets = petRepository.findAll();
        return allPets;
    }
}
