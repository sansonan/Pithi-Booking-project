package com.sonan.pithi_booking.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String type; // e.g., BOOKING, REVIEW, GENERAL

    private Boolean isRead;

    private LocalDateTime createdAt;

    private Long userId; // or vendorId depending on your design

}

