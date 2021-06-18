package com.leverx.leverxspringbootapp.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerParamExchangePets {

    @NotBlank
    private long firstOwnerId;

    @NotBlank
    private long secondOwnerId;

    @NotBlank
    private long firstOwnerPetId;

    @NotBlank
    private long secondOwnerPetId;
}
