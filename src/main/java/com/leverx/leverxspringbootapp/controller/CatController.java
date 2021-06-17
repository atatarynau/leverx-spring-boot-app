package com.leverx.leverxspringbootapp.controller;

import com.leverx.leverxspringbootapp.mapper.CatParamConverter;
import com.leverx.leverxspringbootapp.param.CatParam;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.service.CatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(path = "/pet/cat")
@AllArgsConstructor
public class CatController {

    private final CatService catService;
    private final CatParamConverter catParamConverter;

    @GetMapping(path = "{id}")
    public ResponseEntity<Cat> getById(@PathVariable("id") Long id) {
        log.info(String.format("Try to find cat with id '%s'", id));
        Cat byId = catService.getById(id);
        log.info("Cat was found. Cat: " + byId);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cat> save(@Valid @RequestBody CatParam catParam) {
        log.info("Try to save cat: " + catParam);
        long ownerId = catParam.getOwnerId();
        Cat cat = catParamConverter.toEntity(catParam);
        Cat save = catService.save(cat, ownerId);
        log.info("Cat was saved.");
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        log.info(String.format("Try to delete cat by id '%s'", id));
        catService.deleteById(id);
        log.info("Cat was delete.");
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody CatParam catParam) {
        Cat cat = catParamConverter.toEntity(catParam);
        cat.setId(id);
        catService.update(cat);
        return new ResponseEntity<>("Cat was update", HttpStatus.OK);
    }
}
