package com.example.lab6.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeManagementSystem {

    @NotNull(message = "please enter an id you can't leave it empty")
    @Min(value = 100,message = "please enter in id it least have 3 characters")
    private int ID;


    @NotEmpty(message = "please enter a name you can't leave it empty")
    @Size(min = 5,message = "please enter a name at least have 5 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Name can only contain alphabetic characters")
    private String name;


    @NotEmpty(message = "Please enter an email you can't leave it empty")
    @Email(message = "Please enter a valid email")
    private String email;


    @NotEmpty(message = "please enter a phone number you can't leave it empty")
    @Pattern(regexp= "^05\\d{8}$",message = "The phone number must start with 05 and contain exactly 10 digits")
    @NotBlank(message = "The phone number can't contain space")
    private String phoneNumber;


    @NotNull(message = "please enter an age you can't leave it empty")
    @Positive(message = "please enter a correct age")
    @Min(value = 26,message = "The age must be more than 26")
    private int age;


    @NotEmpty(message = "please enter a position you can't leave it empty")
    @Pattern(regexp = "supervisor|coordinator|",message = "please enter a correct position: supervisor or coordinator")
    private String position;


    private boolean onLeave;


    @NotNull(message = "please enter a date as: yyyy-mm-dd")
    @PastOrPresent(message = "please enter a hair date in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;


    @NotNull(message = "please enter an annual leave you can't leave it empty")
    @Positive(message = "please enter a positive number in annual leave")
    private int annualLeave;
}
