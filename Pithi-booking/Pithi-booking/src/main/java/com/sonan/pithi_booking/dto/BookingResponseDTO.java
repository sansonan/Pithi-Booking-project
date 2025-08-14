package com.sonan.pithi_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDTO {
    private Long id;
    private Long productId;
    private Long customerId;
    private LocalDateTime bookingTime;
    private String message;
    private LocalDateTime createdAt;
    private String status;
}
