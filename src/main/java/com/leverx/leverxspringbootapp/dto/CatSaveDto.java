package com.leverx.leverxspringbootapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CatSaveDto extends PetSaveDto{
    private boolean isHasWool;
}
