package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.DogParamConverter;
import com.leverx.leverxspringbootapp.param.DogParam;
import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.param.OwnerParam;
import com.leverx.leverxspringbootapp.service.DogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(path = "pet/dog")
@AllArgsConstructor
public class DogController {

    private final DogService dogService;

    private final DogParamConverter dogParamConverter;

    @GetMapping(path = "{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable("id")long id){
        log.info("Try to find dog by id '"+id+"'");
        Dog byId = dogService.getById(id);
        log.info("Dog was found. Dog: "+byId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dog> saveDog(@Valid @RequestBody DogParam dogParam){
        log.info("Try to save dog: "+dogParam);
        long ownerId = dogParam.getOwnerId();
        Dog dog = dogParamConverter.ToEntity(dogParam);
        Dog save = dogService.save(dog, ownerId);
        log.info("Dog was saved.");
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteDog(@PathVariable("id")long id){
        log.info("Try to delete dog by id '"+id+"'");
        dogService.deleteById(id);
        log.info("Dog was delete.");
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateDog(@PathVariable("id") long id, @RequestBody DogParam dogParam){
        Dog dog = dogParamConverter.ToEntity(dogParam);
        dog.setId(id);
        dogService.update(dog);
        return new ResponseEntity<>("Dog was update",HttpStatus.OK);
    }
}
