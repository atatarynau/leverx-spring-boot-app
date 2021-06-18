package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.param.CatParamConverter;
import com.leverx.leverxspringbootapp.param.CatParam;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.service.CatService;
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
@RequestMapping(path = "/pet/cat")
@AllArgsConstructor
public class CatController {

    public static final String CAT_GET_ID = "/{id}";
    public static final String CAT_DELETE_ID = "/{id}";
    public static final String CAT_UPDATE_ID = "/{id}";

    private final CatService catService;
    private final CatParamConverter catParamConverter;

    @GetMapping(CAT_GET_ID)
    public ResponseEntity<Cat> getById(@PathVariable("id") Long id) {
        Cat catById = catService.getById(id);
        return new ResponseEntity<>(catById, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cat> save(@Valid @RequestBody CatParam catParam) {
        long ownerId = catParam.getOwnerId();
        Cat catEntity = catParamConverter.toEntity(catParam, Cat.class);
        Cat catFromBd = catService.save(catEntity, ownerId);
        return new ResponseEntity<>(catFromBd, HttpStatus.OK);
    }

    @DeleteMapping(CAT_DELETE_ID)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        catService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(CAT_UPDATE_ID)
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody CatParam catParam) {
        Cat catEntity = catParamConverter.toEntity(catParam, Cat.class);
        catEntity.setId(id);
        catService.update(catEntity);
        return new ResponseEntity<>("Cat was update", HttpStatus.OK);
    }
}
