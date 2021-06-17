package com.leverx.leverxspringbootapp.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerParamExchangePets {

    @NotNull
    @NotBlank
    private long firstOwnerId;
    @NotNull
    @NotBlank
    private long secondOwnerId;
    @NotNull
    @NotBlank
    private long firstOwnerPetId;
    @NotNull
    @NotBlank
    private long secondOwnerPetId;
}
