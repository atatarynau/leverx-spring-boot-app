package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.param.CatParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CatParamConverter {

    private final ModelMapper mapper;

    public Cat toEntity(CatParam catParam){
        Cat cat = mapper.map(catParam, Cat.class);
        return cat;
    }

    public CatParam toDto(Cat cat){
        CatParam catParam = mapper.map(cat, CatParam.class);
        return catParam;
    }
}
