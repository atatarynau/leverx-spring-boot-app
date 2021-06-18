package com.leverx.leverxspringbootapp.mapper.param;

import com.leverx.leverxspringbootapp.entity.Owner;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OwnerParamConverter extends AbstractParamConverter<Owner> {

    public OwnerParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
