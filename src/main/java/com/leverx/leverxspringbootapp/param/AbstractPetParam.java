package com.leverx.leverxspringbootapp.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractPetParam {

    @Pattern(regexp = "[A-Za-z-\\s]{3,15}", message = "You can use only upper and lower case, '-' and spaces")
    @NotNull
    private String breed;

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    @NotNull
    private String name;

    @Size(max = 130, message = "Incorrect age")
    @NotNull
    private int age;

    @NotNull
    private long ownerId;
}
