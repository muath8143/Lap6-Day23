package com.example.nusuk.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingModel {

    @NotNull(message="Booking ID is required")
    @Min(value =100, message ="Booking ID must be at least 3 digits")
    private int bookingId;

    @NotNull(message="Pilgrim information is required")
    private PilgrimModel pilgrim;

    @NotNull(message="Campaign information is required")
    private CampaignModel campaign;

    @NotEmpty(message ="Visit type is required")
    @Pattern(regexp= "Umrah|Hajj",message ="Visit type must be one of: Umrah,Hajj")
    private String visitType;

    @NotNull(message= "Visit date is required")
    @FutureOrPresent(message= "Visit date cannot be in the past")
    private LocalDateTime visitDate;

    @NotNull(message= "Visit time is required")
    private LocalDateTime visitTime;

    @NotEmpty(message ="Location is required")
    @Pattern(regexp ="Haram|Mina|Arafah|Muzdalifah", message="Location must be one of: Haram, Mina, Arafah, Muzdalifah")
    private String location;

    @NotEmpty(message = "Status is required")
    @Pattern(regexp= "Booked|Completed|Canceled",message="Status must be one of: Booked, Completed, Canceled")
    private String status;
}
