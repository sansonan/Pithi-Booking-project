package com.sonan.pithi_booking.service;

import com.sonan.pithi_booking.entity.Booking;
import java.util.List;

public interface BookingService {
    Booking createBooking(Booking booking);
    Booking getBookingById(Long id);
    List<Booking> getAllBookings();
    Booking updateBooking(Long id, Booking bookingData);
    void deleteBooking(Long id);
}



