package com.leverx.leverxspringbootapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerSaveDto {

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    private String lastName;

    @Pattern(regexp = "[A-Z]{2}[0-9]{7}", message = "Incorrect passport number; Example 'HB1111111'")
    private String passportNumber;

    @Pattern(regexp = "(\\+375|80)(44|29|33)[0-9]{7}", message = "Incorrect number. Start number with +375 or 80.")
    private String phoneNumber;
}
