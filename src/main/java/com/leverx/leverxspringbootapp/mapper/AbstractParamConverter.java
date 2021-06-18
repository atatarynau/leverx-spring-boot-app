package com.leverx.leverxspringbootapp.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public abstract class AbstractParamConverter<E>{

    private final ModelMapper mapper;

    public <P> E toEntity(P param, Class<E> clazz){
        return mapper.map(param, clazz);
    }

    public <P> P toParam(E entity, Class<P> clazz){
        return mapper.map(entity, clazz);
    }
}
