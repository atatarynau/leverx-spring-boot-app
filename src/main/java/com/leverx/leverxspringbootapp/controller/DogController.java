package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.DogParamConverter;
import com.leverx.leverxspringbootapp.param.DogParam;
import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.service.DogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "pets/dogs")
@RequiredArgsConstructor
public class DogController {

    public static final String DOG_PATH_ID = "/{id}";

    private final DogService dogService;
    private final DogParamConverter dogParamConverter;

    @GetMapping(DOG_PATH_ID)
    public ResponseEntity<Dog> getById(@PathVariable("id") Long id) {

        Dog dogById = dogService.getById(id);
        return ResponseEntity.ok(dogById);
    }

    @PostMapping
    public ResponseEntity<Dog> save(@Valid @RequestBody DogParam dogParam) {

        long ownerId = dogParam.getOwnerId();
        Dog dogEntity = dogParamConverter.toEntity(dogParam, Dog.class);
        Dog dogFromBd = dogService.save(dogEntity, ownerId);
        return ResponseEntity.ok(dogFromBd);
    }

    @DeleteMapping(DOG_PATH_ID)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        dogService.deleteById(id);
        return ResponseEntity.ok("Delete was performed");
    }

    @PutMapping(DOG_PATH_ID)
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody DogParam dogParam) {

        Dog dogEntity = dogParamConverter.toEntity(dogParam, Dog.class);
        dogEntity.setId(id);
        dogService.update(dogEntity);
        return ResponseEntity.ok("Dog was update");
    }
}
