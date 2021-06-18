package com.leverx.leverxspringbootapp.mapper.param;

import com.leverx.leverxspringbootapp.entity.Cat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CatParamConverter extends AbstractParamConverter <Cat>{

    public CatParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
