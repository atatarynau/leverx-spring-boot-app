package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.param.DogParam;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DogParamConverter extends AbstractParamConverter<Dog, DogParam> {

    public DogParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
