package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.entity.Cat;
import com.leverx.leverxspringbootapp.param.CatParam;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CatParamConverter extends AbstractParamConverter <Cat, CatParam>{

    public CatParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
