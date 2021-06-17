package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.param.OwnerParam;
import com.leverx.leverxspringbootapp.entity.Owner;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OwnerParamConverter {

    private final ModelMapper mapper;

    public Owner toEntity(OwnerParam ownerParam) {
        Owner owner = mapper.map(ownerParam, Owner.class);
        return owner;
    }

    public OwnerParam toDto(Owner owner) {
        OwnerParam ownerParam = mapper.map(owner, OwnerParam.class);
        return ownerParam;
    }

}
