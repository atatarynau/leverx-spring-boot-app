package com.leverx.leverxspringbootapp.resource;


import com.leverx.leverxspringbootapp.dto.CatSaveDto;
import com.leverx.leverxspringbootapp.dto.OwnerSaveDto;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.service.CatService;
import com.leverx.leverxspringbootapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/owner")
public class OwnerResource {

    @Autowired
    private OwnerService ownerService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable("id")long id){
        Owner byId = ownerService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Owner> saveOwner(@Valid @RequestBody OwnerSaveDto ownerSaveDto){
        Owner owner = ownerService.toEntity(ownerSaveDto);
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
}
