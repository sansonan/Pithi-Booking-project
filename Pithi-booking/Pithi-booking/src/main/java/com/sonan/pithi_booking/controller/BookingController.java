package com.sonan.pithi_booking.controller;

import com.sonan.pithi_booking.dto.BookingDTO;
import com.sonan.pithi_booking.dto.BookingRequestDTO;
import com.sonan.pithi_booking.dto.BookingResponseDTO;
import com.sonan.pithi_booking.entity.Booking;
import com.sonan.pithi_booking.repository.ProductRepository;
import com.sonan.pithi_booking.repository.UserRepository;
import com.sonan.pithi_booking.service.BookingService;
import com.sonan.pithi_booking.service.util.BookingMapper;
import com.sonan.pithi_booking.service.util.BookingMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final BookingMappers bookingMappers;

    @PostMapping("/create")
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO requestDTO) {
        Booking booking = bookingMappers.fromRequestDTO(requestDTO);

        booking.setProduct(productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id " + requestDTO.getProductId())));
        booking.setCustomer(userRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + requestDTO.getCustomerId())));

        Booking saved = bookingService.createBooking(booking);
        return ResponseEntity.ok(bookingMappers.toResponseDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingMappers.toResponseDTO(booking));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        List<BookingDTO> dtos = bookings.stream()
                .map(bookingMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        Booking booking = bookingMapper.toEntity(bookingDTO);

        booking.setProduct(productRepository.findById(bookingDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id " + bookingDTO.getProductId())));
        booking.setCustomer(userRepository.findById(bookingDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + bookingDTO.getCustomerId())));

        Booking updated = bookingService.updateBooking(id, booking);
        return ResponseEntity.ok(bookingMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}

