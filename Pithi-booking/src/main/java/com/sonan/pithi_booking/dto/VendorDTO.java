package com.sonan.pithi_booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VendorDTO {
    @NotNull(message = "Vendor ID is required")
    private Long id;
    @NotBlank
    private String businessName;
    private String description;
    private String location;
    private BigDecimal rating;
    @NotNull
    private UserDTO user;
}
