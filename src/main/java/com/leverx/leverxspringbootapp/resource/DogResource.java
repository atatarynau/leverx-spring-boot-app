package com.leverx.leverxspringbootapp.resource;

import com.leverx.leverxspringbootapp.dto.CatSaveDto;
import com.leverx.leverxspringbootapp.dto.DogSaveDto;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.DogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(path = "pet/dog")
public class DogResource {

    @Autowired
    private DogService dogService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable("id")long id){
        log.info("Try to find dog by id '"+id+"'");
        Dog byId = dogService.getById(id);
        log.info("Dog was found. Dog: "+byId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dog> saveDog(@Valid @RequestBody DogSaveDto dogSaveDto){
        log.info("Try to save dog: "+dogSaveDto);
        long ownerId = dogSaveDto.getOwnerId();
        Dog dog = dogService.toEntity(dogSaveDto);
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
}
