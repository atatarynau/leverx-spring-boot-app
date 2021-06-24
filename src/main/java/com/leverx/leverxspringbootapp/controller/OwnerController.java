package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.OwnerParamConverter;
import com.leverx.leverxspringbootapp.param.OwnerParam;
import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.param.OwnerParamExchangePets;
import com.leverx.leverxspringbootapp.service.OwnerService;
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
@RequestMapping(path = "/owners")
@RequiredArgsConstructor
public class OwnerController {

    public static final String OWNER_PATH_ID = "/{id}";
    public static final String OWNER_PATH_KILL_ID = "/kill/{id}";
    public static final String OWNER_PATH_EXCHANGE_PETS = "/exchange";

    private final OwnerService ownerService;
    private final OwnerParamConverter ownerParamConverter;


    @GetMapping(OWNER_PATH_ID)
    public ResponseEntity<Owner> getById(@PathVariable("id") Long id) {

        Owner ownerById = ownerService.getById(id);
        return ResponseEntity.ok(ownerById);
    }

    @PostMapping
    public ResponseEntity<Owner> save(@Valid @RequestBody OwnerParam ownerParam) {

        Owner ownerEntity = ownerParamConverter.toEntity(ownerParam, Owner.class);
        Owner ownerFromBd = ownerService.save(ownerEntity);
        return ResponseEntity.ok(ownerFromBd);
    }

    @DeleteMapping(OWNER_PATH_ID)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {

        ownerService.deleteById(id);
        return ResponseEntity.ok("Delete was performed");
    }

    @PutMapping(OWNER_PATH_KILL_ID)
    public ResponseEntity<String> killById(@PathVariable("id") Long id) {

        ownerService.killOwnerById(id);
        return ResponseEntity.ok("Owner with id " + id + "' was killed");
    }

    @PutMapping(OWNER_PATH_ID)
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody OwnerParam ownerParam) {

        Owner ownerEntity = ownerParamConverter.toEntity(ownerParam, Owner.class);
        ownerEntity.setId(id);
        ownerService.update(ownerEntity);
        return ResponseEntity.ok("Owner was update");
    }

    @PostMapping(OWNER_PATH_EXCHANGE_PETS)
    public ResponseEntity<String> exchangePets(@RequestBody OwnerParamExchangePets ownerParamExchangePets) {

        ownerService.exchangePets(ownerParamExchangePets);
        return ResponseEntity.ok("Exchange was finished");
    }
}
