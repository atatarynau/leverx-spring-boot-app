package com.leverx.leverxspringbootapp.mapper;

import com.leverx.leverxspringbootapp.entity.Dog;
import com.leverx.leverxspringbootapp.param.DogParam;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DogParamConverter {

    private final ModelMapper mapper;

    public Dog ToEntity(DogParam dogParam){
        Dog dog = mapper.map(dogParam, Dog.class);
        return dog;
    }

    public DogParam ToDto(Dog dog){
        DogParam dogParam = mapper.map(dog, DogParam.class);
        return dogParam;
    }

}
