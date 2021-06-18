package com.leverx.leverxspringbootapp.mapper.param;

import com.leverx.leverxspringbootapp.entity.Dog;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DogParamConverter extends AbstractParamConverter<Dog> {

    public DogParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
