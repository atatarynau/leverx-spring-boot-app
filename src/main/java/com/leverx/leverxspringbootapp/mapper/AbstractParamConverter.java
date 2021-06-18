package com.leverx.leverxspringbootapp.mapper;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;



@AllArgsConstructor
@Component
public abstract class AbstractParamConverter<E>{

    private final ModelMapper mapper;

    public <P> E toEntity(P param, Class<E> clazz){
        return mapper.map(param, clazz);
    }

    public <P> P toParam(E model, Class<P> clazz){
        return mapper.map(model, clazz);
    }
}
