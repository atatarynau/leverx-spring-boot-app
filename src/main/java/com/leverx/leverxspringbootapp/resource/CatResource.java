package com.leverx.leverxspringbootapp.resource;

import com.leverx.leverxspringbootapp.dto.CatSaveDto;
import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/pet/cat")
public class CatResource {

    @Autowired
    private CatService catService;

    @GetMapping(path = "{id}")
    public ResponseEntity<Cat> getCatById(@PathVariable("id")long id){
        Cat byId = catService.getById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cat> saveCat(@Valid @RequestBody CatSaveDto catSaveDto){
        long ownerId = catSaveDto.getOwnerId();
        Cat cat = catService.toEntity(catSaveDto);
        Cat save = catService.save(cat, ownerId);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<String> deleteCat(@PathVariable("id")long id){
        catService.deleteById(id);
        return new ResponseEntity<>("Delete was performed", HttpStatus.OK);
    }
}
