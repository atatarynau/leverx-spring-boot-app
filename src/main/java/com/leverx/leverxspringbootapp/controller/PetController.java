package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.entity.Pet;
import com.leverx.leverxspringbootapp.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getById(@PathVariable("id") Long id){

        Pet pet = petService.getById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAll(){

        List<Pet> pets = petService.getAllPets();
        return ResponseEntity.ok(pets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pet>> deleteById(@PathVariable("id") Long id){

        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
