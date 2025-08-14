package com.sonan.pithi_booking.dto;

import com.sonan.pithi_booking.enums.BookingStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookingDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Long productId;      // 👉 REQUIRED for mapping in controller
    private Long customerId;
    private LocalDateTime bookingTime;
    private String message;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String status;
    private int quantity;
}
