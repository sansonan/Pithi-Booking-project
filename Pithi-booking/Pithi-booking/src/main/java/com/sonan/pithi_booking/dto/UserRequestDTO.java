package com.sonan.pithi_booking.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String username;
    private String email;
    private String password;  // input only — we do not expose this in response
    private String role;
}
