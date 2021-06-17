package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.OwnerParamConverter;
import com.leverx.leverxspringbootapp.param.OwnerParam;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping(path = "/owner")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;
    private final OwnerParamConverter ownerParamConverter;

    @GetMapping(path = "{id}")
    public ResponseEntity<Owner> getById(@PathVariable("id") Long id) {
        Owner owner = ownerService.getById(id);
        return new ResponseEntity<>(owner, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Owner> save(@Valid @RequestBody OwnerParam ownerParam) {
        Owner ownerEntity = ownerService.save(ownerParamConverter.toEntity(ownerParam));
        return new ResponseEntity<>(ownerEntity, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        ownerService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "/kill/{id}")
    public ResponseEntity<String> killById(@PathVariable("id") Long id) {
        ownerService.killOwnerById(id);
        return new ResponseEntity<>("Owner with id " + id + "' was killed", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody OwnerParam ownerParam) {
        Owner owner = ownerParamConverter.toEntity(ownerParam);
        owner.setId(id);
        ownerService.update(owner);
        return new ResponseEntity<>("Owner was update", HttpStatus.OK);
    }
}
