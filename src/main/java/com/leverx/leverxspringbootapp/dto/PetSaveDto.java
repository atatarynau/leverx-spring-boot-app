package com.leverx.leverxspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PetSaveDto {

    @Pattern(regexp = "[A-Za-z-\\s]{3,15}", message = "You can use only upper and lower case, '-' and spaces")
    @NotBlank
    private String breed;

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    private String name;

    @Size(max = 130, message = "Incorrect age")
    private String age;

    private long ownerId;
}
