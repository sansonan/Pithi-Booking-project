package com.sonan.pithi_booking.service.impl;


import com.sonan.pithi_booking.entity.Booking;
import com.sonan.pithi_booking.repository.BookingRepository;

import com.sonan.pithi_booking.service.BookingService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;


    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(Long id, Booking bookingData) {
        Booking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));

        if (bookingData.getBookingTime() != null) {
            existing.setBookingTime(bookingData.getBookingTime());
        }
        if (bookingData.getStatus() != null) {
            existing.setStatus(bookingData.getStatus());
        }
        if (bookingData.getMessage() != null) {
            existing.setMessage(bookingData.getMessage());
        }

        return bookingRepository.save(existing);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        bookingRepository.delete(existing);
    }
}

