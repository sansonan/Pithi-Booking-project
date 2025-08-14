package com.sonan.pithi_booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDTO {
    private Long id;
    private ProductDTO product;
    private UserDTO customer;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
