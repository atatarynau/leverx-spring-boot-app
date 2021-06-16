package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.OwnerParamConverter;
import com.leverx.leverxspringbootapp.param.OwnerParam;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    private final OwnerParamConverter ownerParamConverter;

    @GetMapping(path = "{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id")long id){
        Owner byId = ownerService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Owner> saveOwner(@Valid @RequestBody OwnerParam ownerParam){
        Owner owner = ownerParamConverter.toEntity(ownerParam);
        Owner ownerFromBd = ownerService.save(owner);
        return new ResponseEntity<>(ownerFromBd, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable("id")long id){
        ownerService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "/kill/{id}")
    public ResponseEntity<String> killOwner(@PathVariable("id") long id){
        ownerService.killOwnerById(id);
        return new ResponseEntity<>("Owner with id "+id+"' was killed", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> updateOwner(@PathVariable("id") long id, @RequestBody OwnerParam ownerParam){
        Owner owner = ownerParamConverter.toEntity(ownerParam);
        owner.setId(id);
        ownerService.update(owner);
        return new ResponseEntity<>("Owner was update",HttpStatus.OK);
    }
}
