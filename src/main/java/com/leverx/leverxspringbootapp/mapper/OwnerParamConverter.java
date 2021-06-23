package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.entity.Owner;
import com.leverx.leverxspringbootapp.param.OwnerParam;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OwnerParamConverter extends AbstractParamConverter<Owner, OwnerParam> {

    public OwnerParamConverter(ModelMapper mapper) {
        super(mapper);
    }
}
