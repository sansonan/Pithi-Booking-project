package com.sonan.pithi_booking.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@RequiredArgsConstructor
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    public ApiException(HttpStatus status, String message, Object... args) {
        this.status = status;
        this.message = String.format(message, args);
    }
}
