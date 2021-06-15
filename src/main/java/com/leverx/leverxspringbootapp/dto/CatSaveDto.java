package com.leverx.leverxspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CatSaveDto extends PetSaveDto{
    private boolean isHasWool;
}
