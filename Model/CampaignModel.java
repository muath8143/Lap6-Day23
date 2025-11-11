package com.example.nusuk.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CampaignModel {

    @NotNull(message ="Campaign ID is required")
    @Min(value =100,message ="Campaign ID must be at least 3 digits")
    private int campaignId;

    @NotEmpty(message ="Campaign name is required")
    @Size(min= 3,max =50,message="Campaign name must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message="License number is required")
    @Pattern(regexp="^\\d{8}$",message="License number must contain exactly 8 digits")
    private String licenseNumber;

    @NotEmpty(message="Country is required")
    private String country;

    @NotEmpty(message="Contact number is required")
    @Pattern(regexp ="^(05\\d{8}|\\+9665\\d{8})$",message ="Contact number must start with 05 or +9665 and contain 10 digits")
    private String contactNumber;

    @NotNull(message ="Capacity is required")
    @Min(value= 1,message="Capacity must be at least 1")
    @Max(value =500,message ="Capacity cannot exceed 500 pilgrims")
    private int capacity;

    @NotNull(message="Price per person is required")
    @Positive(message="Price per person must be positive")
    private double pricePerPerson;

    @NotNull(message="Available from date is required")
    @PastOrPresent(message="Available from date cannot be in the future")
    private LocalDateTime availableFrom;

    @NotNull(message ="Available to date is required")
    @Future(message= "Available to date must be in the future")
    private LocalDateTime availableTo;

    @NotNull(message ="Rating is required")
    @Min(value= 1,message ="Rating must be between 1 and 5")
    @Max(value =5,message ="Rating must be between 1 and 5")
    private int rating;
}
