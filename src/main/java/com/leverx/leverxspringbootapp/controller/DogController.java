package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.DogParamConverter;
import com.leverx.leverxspringbootapp.param.DogParam;
import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.service.DogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping(path = "pet/dog")
@AllArgsConstructor
public class DogController {

    public static final String DOG_GET_ID = "/{id}";
    public static final String DOG_DELETE_ID = "/{id}";
    public static final String DOG_UPDATE_ID = "/{id}";

    private final DogService dogService;
    private final DogParamConverter dogParamConverter;

    @GetMapping(DOG_GET_ID)
    public ResponseEntity<Dog> getById(@PathVariable("id") Long id) {
        Dog dogById = dogService.getById(id);
        return new ResponseEntity<>(dogById, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Dog> save(@Valid @RequestBody DogParam dogParam) {
        long ownerId = dogParam.getOwnerId();
        Dog dogEntity = dogParamConverter.toEntity(dogParam, Dog.class);
        Dog dogFromBd = dogService.save(dogEntity, ownerId);
        return new ResponseEntity<>(dogFromBd, HttpStatus.OK);
    }

    @DeleteMapping(DOG_DELETE_ID)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        dogService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(DOG_UPDATE_ID)
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody DogParam dogParam) {
        Dog dogEntity = dogParamConverter.toEntity(dogParam, Dog.class);
        dogEntity.setId(id);
        dogService.update(dogEntity);
        return new ResponseEntity<>("Dog was update", HttpStatus.OK);
    }
}
