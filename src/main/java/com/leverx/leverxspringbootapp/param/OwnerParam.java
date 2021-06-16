package com.leverx.leverxspringbootapp.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerParam {

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    @NotNull
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]{2,8}", message = "First upper case, length from 3 to 9 letters.")
    @NotNull
    private String lastName;

    @Pattern(regexp = "[A-Z]{2}[0-9]{7}", message = "Incorrect passport number; Example 'HB1111111'")
    @NotNull
    private String passportNumber;

    @Pattern(regexp = "(\\+375|80)(44|29|33)[0-9]{7}", message = "Incorrect number. Start number with +375 or 80.")
    @NotNull
    private String phoneNumber;
}
