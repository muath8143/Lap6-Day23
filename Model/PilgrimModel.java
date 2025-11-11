package com.example.nusuk.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PilgrimModel {

    @NotNull(message="Pilgrim ID is required")
    @Min(value=1000, message="Pilgrim ID must be at least 4 digits")
    private int pilgrimId;

    @NotEmpty(message="First name is required")
    @Size(min =2,max=40,message="First name must be between 2 and 40 characters")
    private String firstName;

    @NotEmpty(message="Last name is required")
    @Size(min= 2,max=40,message="Last name must be between 2 and 40 characters")
    private String lastName;

    @NotEmpty(message="Nationality is required")
    private String nationality;

    @NotEmpty(message="Passport number is required")
    @Pattern(regexp= "^[A-Z0-9]{8,10}$",message="Passport number must be 8 to 10 uppercase letters or digits")
    private String passportNumber;

    @NotEmpty(message ="Visa type is required")
    @Pattern(regexp ="Umrah|Hajj",message="Visa type must be one of: Umrah , Hajj")
    private String visaType;

    @NotEmpty(message="Email is required")
    @Email(message ="Email must be a valid format")
    private String email;

    @NotEmpty(message ="Phone number is required")
    @Pattern(regexp ="^05\\d{8}$",message ="Phone number must start with 05 and contain exactly 10 digits")
    private String phoneNumber;

    @NotNull(message ="Age is required")
    @Min(value =18,message ="Age must be 18 or older")
    private int age;
}
