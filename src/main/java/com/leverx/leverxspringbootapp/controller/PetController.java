package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final static String PET_PATH_ID = "/{id}";
    private final static String PET_PATH_GET_ALL = "/all";

    private final PetService petService;

    @GetMapping(PET_PATH_ID)
    public ResponseEntity<Pet> getById(@PathVariable("id") Long id) {

        Pet pet = petService.getById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping(PET_PATH_GET_ALL)
    public ResponseEntity<List<Pet>> getAll() {

        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @DeleteMapping(PET_PATH_ID)
    public ResponseEntity<List<Pet>> deleteById(@PathVariable("id") Long id) {

        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
