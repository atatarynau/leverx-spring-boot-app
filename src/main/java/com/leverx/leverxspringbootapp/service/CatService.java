package com.leverx.leverxspringbootapp.service;

import com.leverx.leverxspringbootapp.dto.CatSaveDto;
import com.leverx.leverxspringbootapp.entity.Cat;

public interface CatService {

    Cat save(Cat cat, long ownerId);
    Cat getById(long id);
    Cat toEntity(CatSaveDto catSaveDto);
    void deleteById(long id);
}
